document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('loginForm');

    loginForm.addEventListener('submit', function(e) {
        const phoneNumber = document.getElementById('memberPhone').value;
        const password = document.getElementById('memberPw').value;

        if (!phoneNumber || !password) {
            e.preventDefault(); // 폼 제출을 막습니다.
            alert('휴대폰 번호와 비밀번호를 모두 입력해주세요.');
        }
    });

    // 에러 메시지가 있을 경우 3초 후 자동으로 사라지게 합니다.
    const errorMessage = document.querySelector('.alert-danger');
    if (errorMessage) {
        setTimeout(() => {
            errorMessage.style.display = 'none';
        }, 3000);
    }
});