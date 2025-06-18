console.log("boardDetail.js in");

modifyBtn.addEventListener('click', () => {

    const modifyBtn = document.getElementById('modifyBtn');
    const deleteBtn = document.getElementById('deleteBtn');
    const title = document.getElementById('t');
    const content = document.getElementById('c');

    // title, content 의 readOnly 를 해제
    // readOnly = true / false
    title.readOnly = false;
    content.readOnly = false;

    // modifyBtn, deleteBtn 버튼 삭제

    modifyBtn.remove();
    deleteBtn.remove();
    
    // 실제로 변경 할 수 있는 버튼으로 변경 id="regBtn" type="submit" 인 버튼 추가
    // <button type="submit" id="regBtn" class="btn btn-warning">수정완료</button>

    let regBtn = document.createElement('button'); // <button></button>
    // button 속정 추가
    regBtn.setAttribute('type', 'submit');
    regBtn.setAttribute('id', 'regBtn');
    regBtn.classList.add('btn', 'btn-warning');
    regBtn.innerText = '수정완료';

    // form 태그의 마지막 자식으로 추가 - form 태그의 가장 마지막에 추가
    document.getElementById('modifyForm').appendChild(regBtn);


    
})