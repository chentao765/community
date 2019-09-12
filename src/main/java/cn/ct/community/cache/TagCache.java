package cn.ct.community.cache;

import cn.ct.community.dto.TagDTO;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TagCache {
    private List<TagDTO> tagDTOS=new ArrayList<>();

    public TagCache(){
        TagDTO language=new TagDTO();
        language.setCategory("开发语言");
        language.setTagName(Arrays.asList("javascript","php","css","html","java","node.js ","python","c++","c","golang",
                "objective-c","typescript","shell" ,"swift","c#","sass" ,"ruby" ,"bash" ,"less" ,"asp.net" ,"lua" ,"scala" ,"coffeescript" ,"actionscript" ,"rust" ,"erlang" ,"perl"));
        tagDTOS.add(language);

        TagDTO framework=new TagDTO();
        framework.setCategory("平台框架");
        framework.setTagName(Arrays.asList("laravel","spring","express","django","flask","yii","ruby-on-rails","tornado","koa","struts"));
        tagDTOS.add(framework);

        TagDTO server=new TagDTO();
        server.setCategory("服务器");
        server.setTagName(Arrays.asList("linux","nginx","docker","apache","ubuntu","centos","缓存","tomcat","负载均衡","unix","hadoop","windows-server"));
        tagDTOS.add(server);

        TagDTO database=new TagDTO();
        database.setCategory("数据库");
        database.setTagName(Arrays.asList("mysql","redis","mongodb","sql","oracle","nosql","memcached","sqlserver","postgresql","sqlite"));
        tagDTOS.add(database);

        TagDTO tool=new TagDTO();
        tool.setCategory("开发工具");
        tool.setTagName(Arrays.asList("git","github","visual-studio-code","vim","sublime-text","xcode","intellij-idea","eclipse","maven","ide","svn","visual-studio","atom","emacs","textmate","hg"));
        tagDTOS.add(tool);

    }
    public static  String isVaild(String tag){
        TagCache tagCache=new TagCache();
        String[] tags = tag.split(",");

        List<String> tagList = tagCache.getTagDTOS().stream().flatMap(tagDTO -> tagDTO.getTagName().stream()).collect(Collectors.toList());
        String inValid = Arrays.stream(tags).filter(t -> !tagList.contains(tag)).collect(Collectors.joining(","));
        if(StringUtils.isBlank(inValid)){
                return null;
        }else{
            return inValid;
        }
    }

}
