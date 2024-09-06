document.addEventListener('DOMContentLoaded', function() {
    const searchButton = document.getElementById('search-button');
    const searchInput = document.getElementById('search-input');
    const searchType = document.getElementById('search-type');
    const viewAllButton = document.getElementById('view-all-button');
    const recentNoticesList = document.getElementById('recent-notices-list');
    const noticeAccordion = document.getElementById('notice-accordion');
    const noticesContainer = document.getElementById('notices-container');

    // 가상의 공지사항 데이터
    const notices = [
        { id: 1, title: "중요 공지사항", content: "중요 공지사항 내용...", author: "관리자", date: "2024/08/21" },
        { id: 2, title: "이용 안내", content: "이용 안내 내용...", author: "운영팀", date: "2024/08/25" },
        { id: 3, title: "시스템 점검 안내", content: "시스템 점검 안내 내용...", author: "기술팀", date: "2024/08/31" },
        { id: 4, title: "새로운 기능 안내", content: "새로운 기능에 대한 설명...", author: "개발팀", date: "2024/09/05" },
        { id: 5, title: "이벤트 안내", content: "다가오는 이벤트에 대한 정보...", author: "마케팅팀", date: "2024/09/10" },
        // 추가 공지사항...
    ];

    // 최근 공지사항 표시
    function displayRecentNotices() {
        recentNoticesList.innerHTML = '';
        notices.slice(0, 3).forEach(notice => {
            const li = document.createElement('li');
            li.textContent = notice.title;
            recentNoticesList.appendChild(li);
        });
    }

    // 아코디언 아이템 생성
    function createAccordionItem(notice) {
        return `
            <div class="accordion-item">
                <div class="accordion-header">
                    <div class="notice-icon"></div>
                    <div class="notice-content">
                        <h3>${notice.title}</h3>
                        <p>${notice.date}</p>
                    </div>
                    <div class="notice-title">${notice.author}</div>
                </div>
                <div class="accordion-content">
                    <div class="accordion-content-inner">
                        <p>${notice.content}</p>
                    </div>
                </div>
            </div>
        `;
    }

    // 공지사항 검색
    function searchNotices() {
        const searchTerm = searchInput.value.toLowerCase();
        const searchBy = searchType.value;
        const filteredNotices = notices.filter(notice =>
            notice[searchBy].toLowerCase().includes(searchTerm)
        );
        displayNotices(filteredNotices, "검색 결과");
    }

    // 모든 공지사항 표시
    function displayAllNotices() {
        displayNotices(notices, "모든 공지사항");
    }

    // 공지사항 표시
    function displayNotices(noticesToShow, title) {
        noticesContainer.innerHTML = `<h2>${title}</h2>`;
        const accordionContainer = document.createElement('div');
        accordionContainer.className = 'accordion';
        noticesToShow.forEach(notice => {
            accordionContainer.innerHTML += createAccordionItem(notice);
        });
        noticesContainer.appendChild(accordionContainer);
        setupAccordion(accordionContainer);
    }

    // 아코디언 기능 설정
    function setupAccordion(container) {
        const accordionItems = container.querySelectorAll('.accordion-item');
        accordionItems.forEach(item => {
            const header = item.querySelector('.accordion-header');
            const content = item.querySelector('.accordion-content');

            header.addEventListener('click', () => {
                const isActive = item.classList.contains('active');

                accordionItems.forEach(otherItem => {
                    otherItem.classList.remove('active');
                    otherItem.querySelector('.accordion-content').style.maxHeight = null;
                });

                if (!isActive) {
                    item.classList.add('active');
                    content.style.maxHeight = content.scrollHeight + 'px';
                }
            });
        });
    }

    // 이벤트 리스너 설정
    searchButton.addEventListener('click', searchNotices);
    viewAllButton.addEventListener('click', displayAllNotices);

    // 초기화
    displayRecentNotices();
    notices.slice(0, 3).forEach(notice => {
        noticeAccordion.innerHTML += createAccordionItem(notice);
    });
    setupAccordion(noticeAccordion);

    // 페이지 로드 시 모든 공지사항 표시
    displayAllNotices();
});