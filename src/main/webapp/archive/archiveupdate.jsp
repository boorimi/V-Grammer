<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    /* 추가된 CSS */
    .dialog-container {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0,0,0,0.4);
    }

    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>
</head>
<body>

<c:forEach items="${archives}" var="archive">
    <form action="ArchiveUpdateC" method="post">
        <div class="archive-contents-update">
            <p style="margin-top: 0px">
                <img class="archive-icon" src="haco_img/icon/${archive.i_icon}" >
            </p>
            <input type="hidden" name="a_pk" value="${archive.a_pk}">
            <div class="archive-membername">${archive.m_name}</div>
            
            <div class="archive-collabo">
                <div>コラボ</div>
                <select name="collabo">
                    <option value="yes">Yes</option>
                    <option value="no">No</option>
                </select>
            </div>
             <div class="archive-collabomember">
             <div>コラボメンバー</div>
                <button type="button" class="openModalButton">${archive.a_collabomember}</button>
				<input class="collaboMember" type="text" name="collabomember" /></div>
            <div class="archive-category">
                <div>カテゴリー</div>
                <select name="category">
                    <option value="雑談">雑談</option>
                    <option value="歌枠">歌枠</option>
                    <option value="ゲーム">ゲーム</option>
                    <option value="企画">企画</option>
                    <option value="ASMR">ASMR</option>
                    <option value="shorts">shorts</option>
                    <option value="切り抜き">切り抜き</option>
                    <option value="オリジナル曲">オリジナル曲</option>
                    <option value="他">他</option>
                </select>
            </div>
            <div class="archive-date">${archive.a_date}</div>
            <div class="archive-time">${archive.a_time}</div>
            <div class="archive-title">Title: ${archive.a_title}</div>
            <div class="archive-thumbnail">
                <img src="${archive.a_thumbnail}" alt="${archive.a_title} Thumbnail">
            </div>
            <button type="submit">수정</button>
        </div>
    </form>
</c:forEach>

              
                <div class="dialog-container" id="myModal">
                    <div class="modal-content">
                        <span id="close">&times;</span>
                        <div id="checkboxForm" class="form-container">
                            <p>Select your options:</p>
                            <label><input type="checkbox" name="collabomember" value="七彩てまり"> 七彩てまり</label><br>
                            <label><input type="checkbox" name="collabomember" value="田中りゅこ"> 田中りゅこ</label><br>
                            <label><input type="checkbox" name="collabomember" value="夜夢瑠紅"> 夜夢瑠紅</label><br>
                            <label><input type="checkbox" name="collabomember" value="赤衣アカメ"> 赤衣アカメ</label><br>
                            <label><input type="checkbox" name="collabomember" value="星ノ音コロン"> 星ノ音コロン</label><br>
                            <label><input type="checkbox" name="collabomember" value="愛咲よつのは"> 愛咲よつのは</label><br>
                            <label><input type="checkbox" name="collabomember" value="玉ノ井もなか"> 玉ノ井もなか</label><br>
                            <label><input type="checkbox" name="collabomember" value="綾坂希穂"> 綾坂希穂</label><br>
                            <label><input type="checkbox" name="collabomember" value="ソフィ・ローズ"> ソフィ・ローズ</label><br>
                            <label><input type="checkbox" name="collabomember" value="天海くりね"> 天海くりね</label><br>
                            <label><input type="checkbox" name="collabomember" value="鳳儚"> 鳳儚</label><br>
                            <label><input type="checkbox" name="collabomember" value="小日向千虎"> 小日向千虎</label><br>
                            <label><input type="checkbox" name="collabomember" value="白砂つな"> 白砂つな</label><br>
                            <label><input type="checkbox" name="collabomember" value="橘シエナ"> 橘シエナ</label><br>
                            <label><input type="checkbox" name="collabomember" value="ミラ・ルプス"> ミラ・ルプス</label><br>
                            <label><input type="checkbox" name="collabomember" value="銀灰まお"> 銀灰まお</label><br>
                            <label><input type="checkbox" name="collabomember" value="リン・ガーネット"> リン・ガーネット</label><br>
                            <label><input type="checkbox" name="collabomember" value="明堂しろね"> 明堂しろね</label><br>
                            <label><input type="checkbox" name="collabomember" value="華糖シェリー"> 華糖シェリー</label><br>
                            <label><input type="checkbox" name="collabomember" value="ぺるぽ"> ぺるぽ</label><br>
                            <label><input type="checkbox" name="collabomember" value="叶望ゆゆ"> 叶望ゆゆ</label><br>
                            <label><input type="checkbox" name="collabomember" value="雫川なのか"> 雫川なのか</label><br>
                            <label><input type="checkbox" name="collabomember" value="堕天しすた"> 堕天しすた</label><br>
                            <label><input type="checkbox" name="collabomember" value="山寧恋"> 山寧恋</label><br>
                            <label><input type="checkbox" name="collabomember" value="翠森アトリ"> 翠森アトリ</label><br>
                            
                            <button type="button" name="collabomember" value="selectedOptions" id="submitButton">apply</button>
                        </div>
                    </div>
                </div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    var openModalButtons = document.querySelectorAll(".openModalButton");
    var closeButton = document.querySelector("#close");
    var submitButton = document.querySelector("#submitButton");
    let activeBtn;
    let activeInput;
    openModalButtons.forEach(function(button) {
        button.addEventListener("click", function(event) {
            event.preventDefault();
            activeBtn = event.target;
            activeInput = event.target.nextElementSibling;
            console.log(activeInput);
            console.log(activeBtn);
            var modal = document.querySelector("#myModal");
            modal.style.display = "block";
        });
    });


    	closeButton.addEventListener("click", function(event) {
            event.preventDefault();
            let modal = this.closest(".dialog-container");
            modal.style.display = "none";
        });

    	submitButton.addEventListener("click", function(event) {
            event.preventDefault();
            console.log("Submit button clicked");
            let form = this.closest(".dialog-container").querySelector(".form-container");
            let selectedOptions = [];
            let checkboxes = form.querySelectorAll('input[type="checkbox"]:checked');
            checkboxes.forEach(function(checkbox) {
                selectedOptions.push(checkbox.value);
            });
            console.log(activeBtn);
            console.log(selectedOptions)
            activeBtn.innerText = selectedOptions.join(",");
    		activeInput.value = selectedOptions.join(",");             
            document.querySelectorAll("#checkboxForm input[type='checkbox']:checked").forEach((chkInput)=>{
            	chkInput.checked = false;
            });
            
            closeButton.click();
        });
});
</script>

</body>
</html>



