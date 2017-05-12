<#import "/spring.ftl" as spring/>
<@spring.bind path= "book" />
<form action="/book/add" method="post">
    <table>
        <tr style="display: none;">
            <td>Id</td>
            <td><@spring.formInput "book.id" fieldType=number/></td>
        </tr>
        <tr>
            <td>Название</td>
            <td><@spring.formInput "book.name" /></td>
        </tr>
        <tr>
            <td>Автор</td>
            <td><@spring.formInput "book.author" /></td>
        </tr>
    </table>
    <input type="submit" value="Create" />
</form>