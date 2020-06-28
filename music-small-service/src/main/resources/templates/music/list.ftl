<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>音乐id</th>
                            <th>歌名</th>
                            <th>类型</th>
                            <th>歌曲</th>
                            <th>封面</th>
                            <th>歌手</th>
                            <#--<th>上传时间</th>-->
                        </tr>
                        </thead>
                        <tbody>

                        <#list musicPage.content as music>
                        <tr>
                            <td align="center" >${music.id?c}</td>
                            <td align="center" >《${music.name}》</td>
                            <td align="center" >${music.type}</td>
                            <td align="center"  >
                                <audio src="/silin/${music.url}"  controls="controls"  ></audio>
                            </td>
                            <td align="center" >
                                <img src="/silin/${music.cover}" width="200" height="200">
                            </td>
                            <td align="center" >${music.singer.name}</td>
                            <#--<td>${music.created}</td>-->
                            <#--<td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>-->
                            <td><a href="/silin/admin/music/delete?id=${music.id?c}">删除</a></td>

                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>