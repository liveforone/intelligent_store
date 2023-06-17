## 회원 서비스 소개
* 회원 시스템은 전반적인 회원에 관해 다루는 시스템입니다.
* Member 엔티티를 사용합니다.

## 회원관리 기술
* 서버의 성능을 중요시 하는 전체적인 정책때문에 회원 관리 또한 세션처럼 서버에서 부담을 주는 방식이 아닌,
* Jwt 토큰을 활용해 서버에 부담을 주지 않는 방식을 채택하였습니다.
* 토큰관리는 가장 보편적인 방식인 프론트엔드의 로컬스토리지에서 jwt토큰을 관리하는것을 전제로 합니다.
* 토큰의 만료시간은 2시간입니다.

## 상세 요구사항
* 회원은 일반회원(MEMBER)과 관리자(ADMIN) 마지막으로 셀러(SELLER), 세 종류가 있다.
* 회원 가입시 실명과 주소를 입력받는다.
* 회원은 비밀번호를 변경할 수 있다.
* 어드민회원은 결제 서비스에 등록되어있는 intelligent store 서비스의 계좌를 변경할 수 있다.
* 회원은 마이페이지에서 내 정보를 볼 수 있다.
* 회원은 탈퇴가 가능하다.
* 회원가입시 마일리지를 동시에 만든다.

## API 설계
### 내부 API
```
[GET] / : 홈(토큰 불필요)
[GET/POST] /signup/member : 일반회원과 어드민 회원가입(토큰 불필요), MemberRequest 형식 필요
[GET/POST] /signup/seller : 셀러회원 회원가입(토큰 불필요), MemberRequest 형식 필요
[GET/POST] /login : 로그인(토큰 불필요)
[GET] /logout : 로그아웃, get으로 받아도 정상 작동(토큰 불필요)
[GET] /my-info : 마이페이지(토큰 필요)
[PUT] /change/email : 이메일 변경, ChangeEmailRequest 형식 필요
[PUT] /change/password : 비밀번호 변경, ChangePasswordRequest 형식 필요
[PUT] /change/address : 주소 변경, ChangeAddressRequest 형식 필요
[DELETE] /withdraw : 회원탈퇴(토큰 필요), text 형식 문자열 비밀번호 필요
[GET] /prohibition : 403 페이지(토큰 불필요)
```
### 외부 API
```
[GET] /provide/my-bankbookNum/{username} : 회원 계좌 번호 제공, 외부 제공 api
[GET] /provide/my-address/{username} : 회원 주소 제공, 외부 제공 api
```

## Json body 예시
```
[일반 유저]
{
  "email" : "aa1234@gmail.com",
  "password" : "1234",
  "realName" : "chan"
  "bankbookNum": "1234567898765",
  "city": "서울",
  "roadNum": "종로1가 1111-1",
  "detail": "101호 101동"
}

[어드민]
{
  "email" : "admin@intelligentstore.com",
  "password" : "1234",
  "realName" : "admin"
  "bankbookNum": "1234567898765",
  "city": "서울",
  "roadNum": "종로1가 1111-1",
  "detail": "101호 101동"
}

[이메일 변경]
{
    "email" : "aa1111@gmail.com"
}

[비밀번호 변경]
{
    "oldPassword" : "1234",
    "newPassword" : "1111"
}

[주소 변경]
{
  "city": "경기도",
  "roadNum": "대왕 판교로 7길",
  "detail": "707동 707호"
}
```

## 셀러의 경우
* 설레는 따로 회원가입한다.
* 로그인은 같이 한다.
* 셀러 회원은 상점을 등록하는 것이 가능해진다.

## 어드민 회원가입
* 어드민 회원가입은 일반 회원 가입과 사뭇다릅니다.
* 어드민은 지정된 id(email)로 가입해야합니다.
* 비밀번호는 어드민이 정하고, 또 바꿀 수 있지만 첫 가입시는 반드시 지정된 이메일을 사용해야합니다.
* 이것을 통해서 어드민인지 파악하고 권한을 매핑하기 때문입니다.
* 이번 프로젝트에서 지정된 어드민 이메일은 admin@intelligentstore.com입니다.
* 비밀번호와 마찬가지로 회원가입 후 변경이 가능합니다.

## 서비스간 통신
### 회원 가입시 마일리지 생성
* kafka를 사용한다.
* user-service에서 produce한다.
```
request : username
topic : create-mileage
```