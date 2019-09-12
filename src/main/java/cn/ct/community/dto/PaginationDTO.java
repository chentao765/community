package cn.ct.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private Integer totalPage;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages=new ArrayList<>();

    public void setPagination(int totalPage, Integer page, Integer size) {





        currentPage=page;
        for (int i=3;i>=1;i--){
            if(page-i>0){
                pages.add(page-i);
            }
        }
        pages.add(page);

        for (int i=1;i<=3;i++){
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }



        //是否显示上一页
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }

        //是否显示下页
        if(page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }




    }



}
