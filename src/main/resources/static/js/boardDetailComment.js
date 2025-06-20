console.log("boardDetailComment.js in");

console.log(bno);

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
                str += `<div class="mb-3">`;
                str += `<span class="input-group-text">${cvo.writer}</span>`;
                str += `<input type="text" class="form-control" placeholder="Username" aria-label="Username" value="${cvo.content}" readonly"></input>`;
                str += `<span class="badge rounded-pill text-bg-info">${cvo.regDate}</span>`;
                str += `<button type="button" class="btn btn-outline-primary btn-sm">
                        수정
                        </button>`; // 수정버튼
                str += `<button type="button" class="btn btn-outline-warning del btn-sm">삭제</button>`; // 삭제버튼
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
