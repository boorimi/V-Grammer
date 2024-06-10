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
  animation: fadeIn 0.5s;
}

@keyframes fadeIn {
  from { opacity: 0; transform: scale(0.9); }
  to { opacity: 1; transform: scale(1); }
}

.modal-content {
  background-color: #fff;
  margin: 2% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 90%;
  max-width: 800px;
  max-height: 90vh;
  overflow-y: auto;
  box-shadow: 0 5px 15px rgba(0,0,0,0.3);
  border-radius: 10px;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  transition: color 0.3s;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

form {
  display: flex;
  flex-wrap: wrap;
  max-height: 80vh;
  overflow-y: auto;
}

form label {
  flex: 1 1 calc(20% - 10px);
  margin: 5px;
  display: flex;
  align-items: center;
}

form input[type="checkbox"] {
  margin-right: 10px;
  transform: scale(1.2);
  cursor: pointer;
}

form button {
  flex: 1 1 100%;
  margin-top: 20px;
  padding: 10px;
  font-size: 16px;
  background-color: #008CBA;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

form button:hover {
  background-color: #007B9E;
}

/* Close modal on background click */
dialog::backdrop {
  background: rgba(0, 0, 0, 0.6);
  animation: backdropFadeIn 0.5s;
}

@keyframes backdropFadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}


</style>
</head>


<body>

<button id="openModal">Open Modal</button>


<dialog id="myModal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <form id="checkboxForm">
      <p>Select your options:</p>
      <label><input type="checkbox" name="option1" value="Option 1"> Option 1</label><br>
      <label><input type="checkbox" name="option2" value="Option 2"> Option 2</label><br>
      <label><input type="checkbox" name="option3" value="Option 3"> Option 3</label><br>
      <label><input type="checkbox" name="option4" value="Option 4"> Option 4</label><br>
      <label><input type="checkbox" name="option5" value="Option 5"> Option 5</label><br>
      <label><input type="checkbox" name="option6" value="Option 6"> Option 6</label><br>
      <label><input type="checkbox" name="option7" value="Option 7"> Option 7</label><br>
      <label><input type="checkbox" name="option8" value="Option 8"> Option 8</label><br>
      <label><input type="checkbox" name="option9" value="Option 9"> Option 9</label><br>
      <label><input type="checkbox" name="option10" value="Option 10"> Option 10</label><br>
      <label><input type="checkbox" name="option11" value="Option 11"> Option 11</label><br>
      <label><input type="checkbox" name="option12" value="Option 12"> Option 12</label><br>
      <label><input type="checkbox" name="option13" value="Option 13"> Option 13</label><br>
      <label><input type="checkbox" name="option14" value="Option 14"> Option 14</label><br>
      <label><input type="checkbox" name="option15" value="Option 15"> Option 15</label><br>
      <label><input type="checkbox" name="option16" value="Option 16"> Option 16</label><br>
      <label><input type="checkbox" name="option17" value="Option 17"> Option 17</label><br>
      <label><input type="checkbox" name="option18" value="Option 18"> Option 18</label><br>
      <label><input type="checkbox" name="option19" value="Option 19"> Option 19</label><br>
      <label><input type="checkbox" name="option20" value="Option 20"> Option 20</label><br>
      <label><input type="checkbox" name="option21" value="Option 21"> Option 21</label><br>
      <label><input type="checkbox" name="option22" value="Option 22"> Option 22</label><br>
      <label><input type="checkbox" name="option23" value="Option 23"> Option 23</label><br>
      <label><input type="checkbox" name="option24" value="Option 24"> Option 24</label><br>
      <label><input type="checkbox" name="option25" value="Option 25"> Option 25</label><br>
      <button type="submit">Submit</button>
    </form>
  </div>
</dialog><script type="text/javascript">
//Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("openModal");

// Get the <span> element that closes the modal
var span = document.querySelector("dialog .close");

// Get the form element
var form = document.getElementById("checkboxForm");

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

// Handle form submission
form.onsubmit = function(event) {
  event.preventDefault(); // Prevent form from submitting the traditional way
  var selectedOptions = [];
  var checkboxes = form.querySelectorAll('input[type="checkbox"]:checked');
  checkboxes.forEach(function(checkbox) {
    selectedOptions.push(checkbox.value);
  });
  alert("Selected options: " + selectedOptions.join(", "));
  modal.close();
}


</script>

</body>
</html>