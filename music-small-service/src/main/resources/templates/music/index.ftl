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
                    <form role="form" method="post" action="/silin/admin/music/save" enctype="multipart/form-data">
                        <div class="form-group">
                            <label>歌名</label>
                            <input name="name" type="text" class="form-control" value="${(music.name)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>封面</label>
                            <#--<img height="200" width="200" src="${(music.cover)!''}" alt="">-->
                            <#--<input name="cover" type="text" class="form-control" value="${(music.cover)!''}"/>-->
                            <input name="coverfile" type="file"  value="${(music.cover)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类型</label>
                            <select name="type" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.type}"
                                            <#if (music.type)?? && music.type == category.type>
                                                selected
                                            </#if>
                                    >${category.name}
                                    </option>
                                </#list>
                            </select>
                        </div>
                        <#--<div class="form-group">-->
                            <#--<label>类型</label>-->
                            <#--<input name="productPrice" type="text" class="form-control" value="${(music.productPrice)!''}"/>-->
                        <#--</div>-->
                        <div class="form-group">
                            <label>歌曲</label>
                            <input name="musicfile" type="file"  value="${(music.url)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>歌手</label>
                            <input name="singerName" type="text" class="form-control" value="${(music.singer.name)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>歌手封面</label>
                            <#--<img height="100" width="100" src="${(music.singer.avatar)!''}" alt="">-->
                            <#--<input name="singerAvatar" type="text" class="form-control" value="${(music.singer.avatar)!''}"/>-->
                            <input name="avatarfile" type="file"  value="${(music.singer.avatar)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>歌词</label>
                        <#--<img height="100" width="100" src="${(music.singer.avatar)!''}" alt="">-->
                        <#--<input name="singerAvatar" type="text" class="form-control" value="${(music.singer.avatar)!''}"/>-->
                            <input name="lrc" type="text"   class="form-control" value="${(music.lrc)!''}"/>
                        </div>


                        <input hidden type="text" name="id" value="${(music.id)!''}">
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>