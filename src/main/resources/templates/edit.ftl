<#import "/spring.ftl" as spring/>
<@spring.bind path= "book" />
<form action="/book/edit/${id}" method="post">
    <table>
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