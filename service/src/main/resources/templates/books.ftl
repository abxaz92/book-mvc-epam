<!DOCTYPE html>
<html lang="en">
<body>
<br>
<br>
<table>
  <tr>
    <td>id</td>
    <td>Name</td>
    <td>Author</td>
  </tr>
<#list books as book>
  <tr>
    <td>${book.id}</td>
    <td>${book.name}</td>
    <td>${book.author}</td>
  </tr>
</#list>
</table>

</body>

</html>
      
