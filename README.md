![image](https://github.com/user-attachments/assets/00c65a26-f0b7-4b23-8280-a2791ebfc55c)
<h1>퍼가요스 마트                  
   (QR코드 무인매장 결제 시스템)</h1>
<p>&nbsp;&nbsp;&nbsp;&nbsp;</p> 

<h1>프로젝트 목적</h1>
의도
      매장 공간 최적화
      기존 상황) 매장 내 키오스크 기계가 물리적 공간을 
                     차지하고 앞사람의 구매 대기 시간이 있음
      변경 후) QR을 찍고 페이지를 통한 구매로 
                  키오스크 공간 제거와 대기 시간 단축시킬 수 있음
서비스
     즉시 구매 가능 
          QR의 주소만 있다면 상품을 구매할 수 있어
          기존의 오프라인 대기 시간 없이 
          즉시 픽업이 가능합니다.

<p>&nbsp;&nbsp;&nbsp;&nbsp;</p> 

<h1>개발 기간</h1>
<h4>2024.08.19 ~ 2024.09.13</h4>
<p>&nbsp;&nbsp;&nbsp;&nbsp;</p> 

# 개발 환경

![개발 환경](https://github.com/user-attachments/assets/67165f36-1838-4b01-9c51-8aad0263421a)

# 팀 소개 및 팀원 소개

### FURGAYO'S MART(퍼가요스 마트)

![플로우차트](https://github.com/user-attachments/assets/9f876858-446e-489e-9509-f103b8d2173a)

<p>&nbsp;&nbsp;&nbsp;&nbsp;</p> 
<p>&nbsp;&nbsp;&nbsp;&nbsp;</p> 

<h1>주요 기능</h1>
<h3>상품관리</h3>
[ 상품 관리 ]

상품 조회
: 관리자는 모든 상품 리스트 또는 검색 상품 리스트 조회 및 상품들을 수정, 삭제할 수 있다.
: 등록 일자, 상품 유형, 현재 날짜로 부터 남은 기간, 상품명으로 상품을 검색할 수 있다.
: 상품의 개수가 10개가 넘어갈 경우 페이지 번호가 생성된다.

상품 등록
: 관리자는 상품을 등록할 수 있다.
: 상품명, 상품 유형, 상품 가격, 유통 기한, 할인율, 입고량, 출고량, 상품 구성, 조리법, 추가 정보를 입력할 수 있으며 대표 이비지, 조리된 사진, 상품 구성 사진을 업로드 할 수 있다.
: 초기화 버튼 클릭 시, 입력한 정보들이 초기화된다.
: 필수 입력 정보를 모두 기입해야 상품 등록이 가능하다.

상품 수정
: 관리자는 상품명, 상품 유형, 상품 가격, 유통 기한, 할인율, 입고량, 출고량, 상품 구성, 조리법, 추가 정보를 입력할 수 있으며 대표 이비지, 조리된 사진, 상품 구성 사진을 수정할 수 있다.
: 초기화 버튼 클릭 시, 기존 정보로 변경된다.
: 필수 입력 정보를 모두 기입해야 상품 수정이 가능하다.

사용자  
상품 조회
: 관리자가 등록한 모든 상품 리스트를 볼 수 있고 원하는 카테고리 필터를 걸수 있다
상품 상세 조회
: 상품 리스트에서 상품을 클릭 시 상품의 정보를 볼 수 있고 수량조절 구매 장바구니 기능을 사용 하고
: 상품에 대한 설명 이미지까지 나온다

# 회원관리 주요 기능

## [회원가입]  User
사용자는 회원가입페이지에서 아이디(휴대폰번호), 비밀번호, 비밀번호확인(비밀번호와 동일해야 회원가입가능), 이름, 이메일을 입력하고 전체동의 부분에 체크를 하고 회원가입을 할 수 있다. 이 때 전체동의 부분에서 필수인 부분은 반드시 체크해야 회원가입을 할 수 있다.
아이디는  01x-xxx-xxxx or 01x-xxxx-xxxx 로 입력해야 하고, 비밀번호는 숫자, 문자, 특수문자를 모두 포함하여 8자이상 입력해야 하고, 이메일주소는 ---@---.--- 형태로 @와 . 을 포함해서 입력해야한다.

## [로그인] User
사용자는 로그인페이지에서 회원가입시 입력한 정보와 일치하는 아이디(휴대폰번호)와 비밀번호를 입력해서 로그인할 수 있다.
아이디찾기, 비밀번호찾기, 회원가입 페이지로 이동할 수 있다.

## [아이디찾기] User
아이디찾기 페이지에서는 회원가입 시 입력한 이름, 이메일주소를 입력하고 확인을 누르면 해당 이메일주소로 아이디를 발송한다. 이를 확인하여 아이디를 찾을 수 있다.

## [비밀번호찾기] User
비밀번호찾기 페이지에서는 회원가입 시 입력한 이름, 아이디, 이메일주소를 입력하면 해당 이메일로 임시비밀번호를 생성해서 전송한다. 이 생성된 임시비밀번호는 DB에 저장되어서 사용자는 임시비밀번호를 입력해서 로그인할 수 있다.

## [마이페이지] User
사용자는 해당페이지에서 본인의 기본정보를 확인할 수 있다. 회원가입 시 입력했던 아이디, 이름, 이메일을 볼 수 있고 관리자여부는 회원가입 시 자동으로 일반회원으로 설정되어 일반회원으로 표시된다.
마이페이지에서는 회원정보수정, 장바구니, 내리뷰내역, 내구매내역으로 이동할 수 있다.

## [회원정보수정] User
마이페이지에서 회원정보수정을 클릭하면 이동할 수 있는데 이곳에서는 회원가입 시 입력했던 아이디를 읽기전용으로 해서 고정으로 보여주고 비밀번호, 이름, 이메일, 쇼핑정보 수신동의(선택)는 수정할 수 있다.
그리고 회원탈퇴버튼을 이곳에서 만들어서 회원탈퇴버튼을 클릭하면 alert창을 띄워서 한번더 탈퇴여부를 묻고 탈퇴동의 시 회원탈퇴가 이루어진다. 탈퇴한 회원의 정보는 삭제된다.

## [장바구니] User
사용자는 장바구니페이지에서 내가 담아둔 상품목록을 확인할 수 있다.
해당 상품의 이름, 이미지, 금액을 확인하고, 수량을 변경할 수 있으며 상품마다 체크박스를 통해 선택상품을 삭제할 수 있고, 결제할 수 있다.
체크박스를 통해 체크를 하면 선택상품의 수량을 합해서 총금액을 띄운다.

## [상품구매] User
사용자는 상품상세페이지에서 구매하기버튼을 누르면 이 곳으로 이동한다.
구매페이지에서는 해당 상품의 이름, 이미지, 금액을 확인하고 구매수량을 선택할 수 있으며, 구매수량 설정시 총금액이 상품*수량만큼 표시되어 나타낸다.
결제하기버튼을 누르면 결제완료페이지로 이동한다.

# 게시판 주요 기능

## [게시판] User

### 공지사항 게시판
1. 사용자는 최신순으로 정렬된 공지사항 리스트를 볼 수 있습니다.
2. 상단의 검색창에서 카테고리(작성자 또는 제목)를 선택하여 공지사항을 검색할 수 있습니다.
3. 공지사항의 주요 기능은 사용자에게 중요한 정보를 전달하는 것입니다.
4. 사용자는 공지사항의 제목과 내용을 자세히 볼 수 있습니다.

### 리뷰 게시판
1. 사용자는 제품 구매 전후에 리뷰를 작성하거나 확인할 수 있습니다.
2. 리뷰 목록은 최신순으로 정렬되어 표시됩니다.
3. 전체 공개된 리뷰 목록을 볼 수 있습니다.
4. 검색창 좌측의 카테고리 버튼을 클릭하여 상품명으로 리뷰를 검색할 수 있습니다.
5. 원하는 리뷰를 클릭하여 제목, 작성자, 상품명, 내용 등을 확인할 수 있습니다.
6. 리뷰 작성 시 제목, 상품명, 내용을 입력하여 등록할 수 있습니다.
7. 본인이 작성한 리뷰만 수정 및 삭제가 가능합니다.

## [게시판] Admin

1. 관리자는 전체적인 게시판 관리 권한을 가집니다.
2. 공지사항 관리:
   - 관리자만 공지사항을 등록, 수정, 삭제할 수 있습니다.
   - 사용자는 공지사항을 등록할 수 없습니다.
3. 리뷰 관리:
   - 상품평 리스트와 상세 내역을 관리할 수 있습니다.
   - 부적절한 리뷰를 삭제할 수 있는 권한이 있습니다.
   - 리뷰에 대한 답글을 달 수 있습니다.

### 메인페이지
![image](https://github.com/user-attachments/assets/145ca2a5-0fca-41c3-a352-0d7ded80a13f)

### 상품페이지
![image](https://github.com/user-attachments/assets/a427cab3-83ac-42e4-b8dd-6a9aeefd6ff5)

### 상품 상세 페이지
![image](https://github.com/user-attachments/assets/9f324f57-0f83-4ac4-aaaa-64016b3925da)

### 신규 상품 페이지
![image](https://github.com/user-attachments/assets/e65cdb7c-07d4-4910-90e6-34f226b206d5)

### 인기 상품 페이지
![image](https://github.com/user-attachments/assets/dafa7b2f-c753-4701-851e-296de63aaa40)

### 한식 페이지
![image](https://github.com/user-attachments/assets/eedfe9d2-4b26-4048-9076-49464be3a5c8)

### 중식 페이지
![image](https://github.com/user-attachments/assets/d45e32be-0db2-4262-b6da-0e980bf882d7)

### 일식 페이지
![image](https://github.com/user-attachments/assets/7978ad17-2d6f-44c9-9ec1-d0cd6e13c6f4)



### 상품 수정 페이지
![5ffd27c86f9d0bf6](https://github.com/user-attachments/assets/8c66da76-9c11-46e7-ae83-caa533a1358c)

### 상품 조회 페이지
![b63d5ccbc1838444](https://github.com/user-attachments/assets/3db4f205-f598-4a73-b72f-d47243c964a4)


### 상품 등록 페이지
![d5ebd07fc24b8e02](https://github.com/user-attachments/assets/10ed24dd-45d6-4355-884f-8cc1d4b21752)

### 회원가입 페이지
![회원가입페이지](https://github.com/user-attachments/assets/6011ee73-13b3-4aa0-a895-76fe35a5394d)

### 로그인 페이지
![로그인페이지](https://github.com/user-attachments/assets/9387b1f9-b5bd-437f-ad07-aaabac03db63)

### 아이디찾기 페이지
![아이디찾기페이지](https://github.com/user-attachments/assets/7aed3cda-7262-49c9-b653-572c780d3ea3)

### 비밀번호찾기 페이지
![비밀번호찾기페이지](https://github.com/user-attachments/assets/5d1d28dc-35ab-4744-8f0b-b8abf00c35ee)

### 마이페이지 페이지
![마이페이지](https://github.com/user-attachments/assets/7bd57071-a768-4caf-8434-9cd46ebf265b)

### 회원정보수정 페이지
![회원정보수정페이지](https://github.com/user-attachments/assets/8b9b1b7a-2c4a-4517-a0d0-de44cf969dca)

### 장바구니 페이지
![장바구니페이지](https://github.com/user-attachments/assets/0d70296b-916a-4dec-9c14-939c290e580f)

### 구매 페이지
![구매페이지](https://github.com/user-attachments/assets/f0398ee3-486d-43ed-a387-da4453103c4a)

### 공지사항 유저 페이지
![스크린샷 2024-09-13 170847](https://github.com/user-attachments/assets/837d5879-fbdd-4e9b-8307-4d349b393f10)

### 리뷰 유저 페이지
![image](https://github.com/user-attachments/assets/d2cb6353-a488-4ee7-9a06-4b87f6b355f3)

### 관리자 메인 페이지
![image](https://github.com/user-attachments/assets/b1ca8210-3b37-4d6e-a227-15da61ce9825)

### 공지사항 관리 페이지
![image](https://github.com/user-attachments/assets/65eef9d5-0f4e-4aad-82cb-89839e1a71ec)

### 리뷰 관리 페이지
![image](https://github.com/user-attachments/assets/f30a9deb-be02-4b4a-baa7-96322b191903)

### 회사 소개 페이지 
![image](https://github.com/user-attachments/assets/9abfcd4c-45cb-4b89-98dd-27cefe050e7b)

### 문의하기 페이지 
![image](https://github.com/user-attachments/assets/0c4e70f0-15cf-4f1b-8461-b0caff78b202)

### 운영정책 페이지 
![image](https://github.com/user-attachments/assets/8a4721a7-5e05-4b21-86ed-ca4342a6d39c)


