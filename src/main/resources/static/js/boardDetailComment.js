console.log("boardDetailComment.js in");

console.log(bno);
console.log(userNickName);

// commentRegBtn 버튼 disabled 값 풀어주기
const emailInput = document.getElementById('email');
const nickInput = document.getElementById('nickName');
const contentInput = document.getElementById('content');
const commentRegBtn = document.getElementById('commentRegBtn');
const printComment = document.getElementById('printComment');

document.getElementById('content').addEventListener('input', () => {
    if(contentInput.value !== ''){
        commentRegBtn.disabled = false;
    }else{
        commentRegBtn.disabled = true;
    }
})

// 댓글 등록 버튼을 누를경우
commentRegBtn.addEventListener('click', () => {

    commentData = {
        writer : nickInput.value,
        content : contentInput.value,
        bno : bno
    }
    
    // 서버로 전송
    commentPostToServer(commentData).then(result => {
        if(result == "ok"){
            alert("댓글이 등록되었습니다.")
            printCommentList(bno);
        }else if(result == "fail"){
            alert("댓글 등록에 실패했습니다.")
        }
    })




})


// comment 출력
function printCommentList(bno){
    // <span class="input-group-text" id="basic-addon1">@</span>
    // <input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1"></input>
    getCommentListFromServer(bno).then(result => {
        console.log(result);
        if(result.length > 0){
            printComment.innerHTML = ''
            // <div class="input-group mb-3">
            // <span class="input-group-text">@</span>
            // <input type="text" class="form-control" placeholder="Server" aria-label="Server">
            // </div>

            for(let cvo of result){
                let str = `<li class="list-group-item" data-cno="${cvo.cno}">`;
                str += `<div class="mb-3 input-group">`;
                str += `<span class="input-group-text">${cvo.writer}</span>`;
                str += `<input type="text" class="form-control" placeholder="content" aria-label="Username" value="${cvo.content}" readonly></input>`;
                str += `<span class="input-group-text">${cvo.regDate}</span>`;
                if(cvo.writer == userNickName){
                    // 작성자 이름과 로그인한 유저의 닉네임이 같을경우에만 수정, 삭제 버튼 출력
                    str += `<button type=button class="btn btn-primary modReg btn-sm" style="display : none;">수정완료</button>`
                    str += `<button type="button" class="btn btn-outline-primary mod btn-sm">
                            수정
                            </button>`; // 수정버튼
                    str += `<button type="button" class="btn btn-outline-warning del btn-sm">삭제</button>`; // 삭제버튼

                }
                str += `</div>`;
                str += `</li>`;
                printComment.innerHTML += str;
            }

        }else{
            // 댓글이 없을경우
            ul.innerHTML = `<li class="list-group-item">Comment List Empty</li>`;
        }

    })
}

// 수정버튼인지 삭제버튼인지
document.addEventListener("click", (e) => {
    let li = e.target.closest('li');
    let updateCommentInput = li.querySelector('input');// 가장 가까운 input의 readonly를 해제하고 포커스를 줄것이다.
    let cno = li.dataset.cno;

    // 삭제
    if(e.target.classList.contains('del')){

        deleteCommentToServer(cno).then(result => {
            if(result == "1"){
                alert('삭제성공')
            }else{
                alert('삭제실패')
            }
            printCommentList(bno);
        })
    }
    // 수정 버튼을 클릭하면
    if(e.target.classList.contains('mod')){

        updateCommentInput.readOnly = false;
        updateCommentInput.focus();

        let modRegBtn = li.querySelector('.modReg');
        let modBtn = li.querySelector('.mod');
        if(modRegBtn){
            modRegBtn.style.display = 'inline-block';
        }
        if(modBtn){
            modBtn.style.display = 'none';
        }
        
    }
    
    if(e.target.classList.contains('modReg')){
        const content = updateCommentInput.value;
        updateCommentToServer(cno, content).then(result => {
            if(result == "1"){
                alert('수정 성공');
            }else{
                alert('수정 실패');
            }
            printCommentList(bno);
        })
        updateCommentInput.readOnly = true;
    }
})



// comment 의 데이터 값 보내기
async function commentPostToServer(commentData){
    try {
        
        const url = "/comment/post";
        const config = {
            method : 'post',
            headers : {
                'Content-Type' : 'application/json; charset=utf-8'
                },
            body : JSON.stringify(commentData) 
            }
    
        const resp = await fetch(url, config);
        const result = await resp.text();

        return result;

    } catch (error) {
        console.log(error);
    }
    
}

// comment 의 데이터 값 가져오기
async function getCommentListFromServer(bno) {
    try {
        
        const resp = await fetch(`/comment/${bno}`);
        const result = await resp.json();
        return result;

    } catch (error) {
        console.log(error);
    }
    
}

// comment 삭제.
async function deleteCommentToServer(cno) {
    try {
        const config = {
            method : 'delete'
        }

        const resp = await fetch(`/comment/delete?cno=${cno}`, config);
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}

// comment 수정.
async function updateCommentToServer(cno, content) {
    try {
        
        const updateCmtData = {
            cno : cno,
            content : content
        }

        const config ={
            method : 'put',
            headers : {
                'Content-Type' : 'application/json; charset=utf-8'
                },
            body : JSON.stringify(updateCmtData)
             
        }
        
        const resp = await fetch(`/comment/updateComment`, config)
        const result = await resp.text();
        return result;

    } catch (error) {
        console.log(error);
    }
}