document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form');
    const passwordInput = document.getElementById('memberPw');
    const passwordConfirmInput = document.getElementById('memberPwConfirm');

    form.addEventListener('submit', function(e) {
        if (passwordInput.value !== passwordConfirmInput.value) {
            e.preventDefault();
            alert('비밀번호가 일치하지 않습니다.');
        }
    });
});

function confirmWithdraw() {
    if (confirm('정말로 회원 탈퇴를 하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
        // 회원 탈퇴 처리를 위한 서버 요청
        document.getElementById('withdrawForm').submit();
    }
}