상점 삭제시 상품 삭제
주소는 이미 회원에 저장되어있음
상점은 하나만 개설 가능
일반 회원이 보는 상점/셀러가 보는 상점(주인)

## 상점 서비스 소개
* 상점 서비스는 상점과 관련된 일을 처리하는 서비스입니다.
* 판매자(seller)와 구매자(member)의 관점 모두에서 일을 처리합니다.

## 상세 요구사항
* 상점 개설은 셀러만 가능하며, 한 셀러당 한 개의 상점만 만들 수 있다.
* 셀러 회원 가입시 상점이 생성된다.
* 상점 조회는 누구나 가능하다.
* 상점에서는 상점 정보 등만 제공하고,
* 상점에 속하는 상품은 내 상품 보기 와 같은 버튼을 클릭시 상품 서비스의 api로 넘어가도록 한다.
* 즉 상점 서비스는 상품 목록으로 넘어가는 게이트 역할을 할뿐 직접적인 상품 목록을 제공하진 않는다.
* 외부식별자로는 회원의 username(uuid)를 사용하며 unique 제약 조건을 건다.

## 상점 상세페이지
* 상점 상세 페이지는 두 종류가 있다.
* 첫째는 모두가 진입가능한 상세페이지이고,
* 두번째는 어드민 페이지와 같이 상점의 주인만 진입 가능한 페이지이다.
* 두번째 경우에는 상세 페이지 진입시 상점 정보 수정이 가능하고,
* 첫째 페이지는 모두가 읽기 작업만 하는 페이지 이다.

## Api 설계
### 내부 API
```
[GET] /shop/home : 전체 상점 조회
[GET] /shop/search : 상점 검색
[GET] /shop/{shopId} : 상점 상세조회
[GET] /shop/info/{email} : 내 상점 조회, 상점의 주인만 접근 가능
[PUT] /shop/change-name/{shopId} : 상호 변경, 상점의 주인만 접근 가능
[PUT] /shop/change-tel/{shopId} : 상점 전화번호 변경, 상점의 주인만 접근 가능
```

## Json body 예시
### 내부 Body
```
[상호 변경]
{
    "shopName": "change shop"
}

[전화번호 변경]
{
    "tel": "01087654321",
}
```
### 외부 Body
```
[상점 등록]
{
  "shopName": "test_shop",
  "tel": "01012345678",
  "username": "dlsfdsnflsfjdslf"
}
```

## 서비스간 통신
### 셀러 회원 가입시 상점 생성
* kafka를 사용한다.
* user-service에서 produce한다.
```
request : username
topic : create-shop
```
### 회원 탈퇴시 상점 삭제
* kafka를 사용한다.
* user-service에서 produce한다.
```
request : username
topic : remove-shop-belong-member
```