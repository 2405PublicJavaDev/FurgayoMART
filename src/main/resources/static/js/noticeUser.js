document.addEventListener('DOMContentLoaded', function() {
    const searchButton = document.getElementById('search-button');
    const searchInput = document.getElementById('search-input');
    const searchType = document.getElementById('search-type');
    const viewAllButton = document.getElementById('view-all-button');
    const recentNoticesList = document.getElementById('recent-notices-list');
    const noticeAccordion = document.getElementById('notice-accordion');
    const noticesContainer = document.getElementById('notices-container');

    // Rest API로 공지사항 가져오기
    function fetchNotices() {
        fetch('/api/notice/list')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // 최근 공지사항과 모든 공지사항에 데이터 반영
                displayRecentNotices(data.notices.slice(0, 3)); // 최근 공지사항
                displayNotices(data.notices, "모든 공지사항"); // 모든 공지사항
            })
            .catch(error => {
                console.error('Error fetching notices:', error);
            });
    }

    // 최근 공지사항 표시
    function displayRecentNotices(notices) {
        recentNoticesList.innerHTML = '';
        notices.forEach(notice => {
            const li = document.createElement('li');
            li.textContent = notice.noticeTitle; // API에 맞게 필드명 수정
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
                        <h3>${notice.noticeTitle}</h3>
                        <p>${new Date(notice.noticeDate).toLocaleDateString()}</p>
                    </div>
                    <div class="notice-title">${notice.noticeWriter}</div>
                </div>
                <div class="accordion-content">
                    <div class="accordion-content-inner">
                        <p>${notice.noticeContent}</p>
                    </div>
                </div>
            </div>
        `;
    }

    // 공지사항 검색
    function searchNotices() {
        const searchTerm = searchInput.value.toLowerCase();
        const searchBy = searchType.value;

        // 서버로 검색 요청 보내기
        fetch(`/api/notice/list?searchType=${searchBy}&searchKeyword=${encodeURIComponent(searchTerm)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                displayNotices(data.notices, "검색 결과"); // 검색된 공지사항 표시
            })
            .catch(error => {
                console.error('Error fetching notices:', error);
            });
    }

    // 모든 공지사항 표시
    function displayAllNotices() {
        fetchNotices(); // 모든 공지사항을 다시 불러옴
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
    fetchNotices(); // 페이지 로드 시 공지사항 목록 가져오기
});
