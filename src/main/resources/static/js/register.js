document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('registerForm');
//    const sendVerificationBtn = document.getElementById('sendVerificationBtn');
//    const verifyEmailBtn = document.getElementById('verifyEmailBtn');
//    const submitBtn = document.getElementById('submitBtn');

    form.addEventListener('submit', function(e) {
        const memberPhone = document.getElementById('memberPhone').value;
        const memberPw = document.getElementById('memberPw').value;
        const memberPwConfirm = document.getElementById('memberPwConfirm').value;
        const memberEmail = document.getElementById('memberEmail').value;

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

//    sendVerificationBtn.addEventListener('click', function() {
//        const email = document.getElementById('memberEmail').value;
//        fetch('/send-verification', {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/x-www-form-urlencoded',
//            },
//            body: `email=${email}`
//        })
//        .then(response => response.text())
//        .then(data => {
//            showMessage(data);
//        })
//        .catch(error => {
//            showMessage('인증 코드 발송 중 오류가 발생했습니다.');
//        });
//    });
//
//    verifyEmailBtn.addEventListener('click', function() {
//        const email = document.getElementById('memberEmail').value;
//        const code = document.getElementById('verificationCode').value;
//        fetch('/verify-email', {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/x-www-form-urlencoded',
//            },
//            body: `email=${email}&code=${code}`
//        })
//        .then(response => response.text())
//        .then(data => {
//            showMessage(data);
//            if (data === '이메일이 성공적으로 인증되었습니다.') {
//                submitBtn.disabled = false;
//            }
//        })
//        .catch(error => {
//            showMessage('이메일 인증 중 오류가 발생했습니다.');
//        });
//    });

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

    function showMessage(message, type = '') {
        const messageDiv = document.getElementById('message');
        messageDiv.textContent = message;
        messageDiv.className = type;  // 메시지 타입에 따른 스타일 적용을 위해 클래스 추가
    }
});