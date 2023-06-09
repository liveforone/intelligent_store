# 마일리지 서비스

## 마일리지 서비스 소개
* 마일리지 서비스는 회원의 마일리지를 관리하는 서비스 입니다.
* 적립급 계산하여 적립을 하거나, 사용하는 등의 일을 하는 서비스 입니다.
* 유저 서비스와 분리함으로써 유저 서비스의 부하를 줄일 수 있습니다.
* 퍼포먼스를 위해 조회를 제외하고는 모두 비동기로 처리합니다.

## 상세 요구사항
* 일반 회원(MEMBER) 회원가입과 동시에 마일리지도 생성되어야한다.
* 외부식별자는 unique로 설정한다. 이유는 1대1로 관계가 맺어지기 때문이다.
* 마이페이지와 주문시 마일리지 조회 api를 제공한다.
* 적립금 계산은 마일리지 서비스에서 한다.
* 적립금은 주문금액의 1%이다.
* 마일리지 적립과 사용은 모두 kafka를 사용한다.
* 적립과 사용은 모두 주문시 발생하므로 두가지의 요청을 한번에 처리하도록한다.
* 주문 취소시 사용한 마일리지 만큼 다시 더하여주고, 총 주문 금액으로 적립된 마일리지를 계산하여 현재 마일리지에서 마이너스 해준다.
* 롤백을 처리할때에는 orderId를 저장할 수 없기 때문에, username, itemPrice, spentMileage 3가지의 정보가 일치하는 최신 주문을 롤백처리하도록 한다.

## API 설계
### 외부 API
```
[GET] /provide/my-mileage/{username} : 마일리지 조회, MileageResponse 전달
[POST] /provide/my-mileage : 마일리지 조회, MileageResponse 전달, 주문시 조회하는 용도, 헤더에 토큰 삽입하여 전달받음
```

## 서비스 간 통신
### 일반 회원 회원가입시 마일리지 생성
* kafka를 사용한다.
* user-service에서 produce한다.
```
request : username
topic : create-mileage
```
### 회원 탈퇴시 마일리지 삭제
* kafka를 사용한다.
* user-service에서 produce한다.
```
request : username
topic : remove-mileage-belong-member
```
### 주문 시 마일리지 사용과 적립
* kafka를 사용한다.
* order-service에서 produce한다.
```
request : MileageRequestWhenOrder
topic : mileage-request-when-order
```
### 마일리지 사용 실패시 주문 롤백
* kafka를 사용한다.
* mileage-service에서 produce한다.
```
request : MileageFailRollbackOrderRequest
topic : mileage-fail-rollback-order
```
### 주문 취소/실패시 마일리지 롤백
* kafka를 사용한다.
* order-service에서 produce한다.
```
request : OrderFailRollbackMileageRequest
topic : order-fail-rollback-mileage
```