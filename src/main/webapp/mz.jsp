<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* Modal Styles */
dialog {
  display: none;
}

dialog[open] {
  display: block;
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

<button id="openModal">Open Modal</button>

<dialog id="myModal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <p>This is a modal dialog using dialog tag.</p>
  </div>
</dialog>
<script type="text/javascript">
//Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("openModal");

// Get the <span> element that closes the modal
var span = document.querySelector("dialog .close");

// When the user clicks the button, open the modal 
btn.onclick = function() {
  modal.showModal();
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.close();
}

// When the user clicks anywhere outside of the modal, close it
modal.onclick = function(event) {
  if (event.target === modal) {
    modal.close();
  }
}

</script>

</body>
</html>