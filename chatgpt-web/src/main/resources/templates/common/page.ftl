<ul class="pagination justify-content-center">
    <li class="page-item <#if page.pageNum==1>disabled</#if>">
        <a class="page-link" href="${requestURI}<#if parameters??>?${parameters}&page<#noparse>=</#noparse>${page.pageNum-1}</#if>" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">上一页</span>
        </a>
    </li>
    <#list 1..6 as i>
    <#if i <= page.pages>
        <#if i==page.pageNum>
            <li class="page-item active">
                <a class="page-link" href="${requestURI}<#if parameters?? && parameters!=''>?${parameters}&page<#noparse>=</#noparse>${i}<#else>?page<#noparse>=</#noparse>${i}</#if>">${i} <span class="sr-only">(${i})</span></a>
            </li>
        <#else>
            <li class="page-item"><a class="page-link" href="${requestURI}<#if parameters??>?${parameters}&page<#noparse>=</#noparse>${i}</#if>">${i}</a></li>
        </#if>
    </#if>
    </#list>
    <li class="page-item <#if page.pageNum==page.pages>disabled</#if>">
        <a class="page-link" href="${requestURI}<#if parameters??>?${parameters}&page<#noparse>=</#noparse>${page.pageNum+1}</#if>" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">下一页</span>
        </a>
    </li>
</ul>