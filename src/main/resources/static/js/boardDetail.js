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

    // file-x 버튼의 클래스 style="visibility:hidden" => "visibility:visible"
    let fileDelBtn = document.querySelectorAll('.file-x');
    for(let btn of fileDelBtn){
        btn.style.visibility = "visible";
        btn.addEventListener('click', () => {
            let uuid = btn.dataset.uuid;
            console.log(uuid);
            fileRemoveToserver(uuid).then(result => {
                if(result === "1"){
                    alert('파일 삭제 성공');
                    btn.closest('li').remove();
                }else{
                    alert('퍄일 삭제 실패')
                }
            })
        })
    }

    // 파일 버튼 disabled 해지
    document.getElementById('trigger').disabled = false;

});

// file-x 비동기 보내서 파일 삭제
async function fileRemoveToserver(uuid) {
    try {
        const url = '/board/file/'+uuid;
        const config = {
            method : 'delete'
        }

        const resp = await fetch(url,config);
        const result = await resp.text();

        return result;

    } catch (error) {
        console.log(error);        
    }
}



