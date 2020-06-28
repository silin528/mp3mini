<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
	<ul class="nav sidebar-nav">
		<li class="sidebar-brand">
			<a href="#">
				音乐管理系统
			</a>
		</li>
		<li>
			<#--<a href="/sell/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>-->
		</li>
		<li class="dropdown open">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i
						class="fa fa-fw fa-plus"></i> 音乐 <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li class="dropdown-header">操作</li>
				<li><a href="/silin/admin/music/list">列表</a></li>
				<li><a href="/silin/admin/music/index">新增</a></li>
			</ul>
		</li>
		<li class="dropdown open">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i
					class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li class="dropdown-header">操作</li>
				<li><a href="/silin/admin/category/list">列表</a></li>
				<li><a href="/silin/admin/category/index">新增</a></li>
			</ul>
		</li>
        <#--<li class="dropdown open">-->
            <#--<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i-->
                    <#--class="fa fa-fw fa-plus"></i> 评价 <span class="caret"></span></a>-->
            <#--<ul class="dropdown-menu" role="menu">-->
                <#--<li class="dropdown-header">操作</li>-->
                <#--<li><a href="/sell/seller/comment/list">列表</a></li>-->
                <#--&lt;#&ndash;<li><a href="/sell/seller/category/index">新增</a></li>&ndash;&gt;-->
            <#--</ul>-->
        <#--</li>-->

		<li class="dropdown open">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i
						class="fa fa-fw fa-plus"></i> 管理员 <span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
				<li class="dropdown-header">操作</li>
				<li><a href="/silin/admin/list">管理员列表</a></li>
				<li><a href="/silin/admin/index">新增管理员</a></li>
			</ul>
		</li>
		<#--<li class="dropdown open">-->
			<#--<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i-->
						<#--class="fa fa-fw fa-plus"></i> 轮播图 <span class="caret"></span></a>-->
			<#--<ul class="dropdown-menu" role="menu">-->
				<#--<li class="dropdown-header">操作</li>-->
				<#--<li><a href="/sell/picture/list">列表</a></li>-->
				<#--<li><a href="/sell/picture/index">新增</a></li>-->
			<#--</ul>-->
		<#--</li>-->

		<li>
			<a href="/silin/admin/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
		</li>
	</ul>
</nav>