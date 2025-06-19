console.log("boardRegister.js in");

document.getElementById('trigger').addEventListener('click', () => {
    document.getElementById('file').click();
})

// 실행 파일 막기, / 5MB 이상 파일 막기

const regExp = new RegExp("\.(exe|sh|bat|jar|dll|msi)$");
const maxSize = 1024*1024*5;

function fileValidation(fileName, fileSize){
    if(regExp.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener('change', (e) => {
    if(e.target.id == 'file'){
        const fileObject = document.getElementById('file').files;
        console.log(fileObject);

        document.getElementById('regBtn').disabled = false;
        const fileZone = document.getElementById('fileZone');
    
        // 이전에 추가한 파일 삭제
        fileZone.innerHTML = '';
        let ul = '<ul class="list-group">';
        let isOk = 1; // 모든 첨부파일에 대해 통과 하는지 체크 *= 누적
        for(let file of fileObject){
            let valied = fileValidation(file.name, file.size);
            isOk *= valied;
            ul += `<li class="list-group-item">`;
            ul += `<div class="mb-3">`;
            ul += `${valied ? '<div class="fw-bold text-success">업로드 가능</div>' : '<div class="fw-bold text-danger">업로드 불가능</div>'}`;
            ul += `${file.name}`;
            ul += `<span class="badge text-bg-${valied ? 'success' : 'danger'} rounded-pill">${file.size}Byte</span>`;
            ul += `</div>`;
            ul += `</li>`;
        }
        ul += '</ul>';
        fileZone.innerHTML = ul;
        
        if(isOk == 0){
            document.getElementById('regBtn').disabled = true;
        }
    }

})