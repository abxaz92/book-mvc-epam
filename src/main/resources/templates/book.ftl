<!DOCTYPE html>
<html lang="en">
<body>
<br>
<br>
<#if book??>
<table>
  <tr>
    <td>Name</td>
    <td>${book.name}</td>
  </tr>
  <tr>
    <td>Author</td>
    <td>${book.author}</td>
  </tr>
</table>
   <#else>
    Not found
</#if>
</body>

</html>
