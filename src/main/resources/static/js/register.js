document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registerForm');
	const submitBtn = document.getElementById('submitBtn');

    form.addEventListener('submit', function(e) {
        const memberPhone = document.getElementById('memberPhone').value;
        const memberPw = document.getElementById('memberPw').value;
        const memberPwConfirm = document.getElementById('memberPwConfirm').value;
        const memberEmail = document.getElementById('memberEmail').value;

		submitBtn.addEventListener('click', () => {
		           console.log('가입하기 버튼 클릭됨');
		       });
		
        if (!validatePhoneNumber(memberPhone)) {
            e.preventDefault();
            showMessage('유효한 휴대폰 번호를 입력해주세요.', 'error');
            return;
        }

        if (memberPw !== memberPwConfirm) {
            e.preventDefault();
            showMessage('비밀번호가 일치하지 않습니다.', 'error');
            return;
        }

        if (!validatePassword(memberPw)) {
            e.preventDefault();
            showMessage('비밀번호는 8자 이상이어야 하며, 숫자, 문자, 특수문자를 모두 포함해야 합니다.', 'error');
            return;
        }

        if (!validateEmail(memberEmail)) {
            e.preventDefault();
            showMessage('유효한 이메일 주소를 입력해주세요.', 'error');
            return;
        }
    });

    function validatePhoneNumber(phone) {
        const regex = /^01[016789]-?\d{3,4}-?\d{4}$/;
        return regex.test(phone);
    }

    function validatePassword(password) {
        const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
        return regex.test(password);
    }

    function validateEmail(email) {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    }

//    function showMessage(message, type = '') {
//        const messageDiv = document.getElementById('message');
//        messageDiv.textContent = message;
//        messageDiv.className = type;  // 메시지 타입에 따른 스타일 적용을 위해 클래스 추가
//    }
});