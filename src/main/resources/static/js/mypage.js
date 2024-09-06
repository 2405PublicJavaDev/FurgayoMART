document.addEventListener('DOMContentLoaded', function() {
    // 프로필 이미지 클릭 이벤트
    const profileImage = document.getElementById('profileImage');
    if (profileImage) {
        profileImage.addEventListener('click', function() {
            alert('프로필 이미지 변경 기능은 아직 구현되지 않았습니다.');
        });
    }

    // 메시지 표시 함수
    function showMessage(message, type = 'info') {
        const messageDiv = document.createElement('div');
        messageDiv.className = `message ${type}-message`;
        messageDiv.textContent = message;
        document.body.appendChild(messageDiv);

        setTimeout(() => {
            messageDiv.remove();
        }, 3000);
    }

    // URL 파라미터에서 메시지 확인
    const urlParams = new URLSearchParams(window.location.search);
    const msg = urlParams.get('msg');
    if (msg) {
        showMessage(decodeURIComponent(msg));
    }

    // 각 섹션의 링크에 클릭 이벤트 리스너 추가
    const links = document.querySelectorAll('.grid-item a');
    links.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const href = this.getAttribute('href');
            showMessage(`${this.textContent} 페이지로 이동합니다.`, 'info');
            setTimeout(() => {
                window.location.href = href;
            }, 1000);
        });
    });
});