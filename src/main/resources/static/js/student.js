$(document).ready(function(){
  /**
   * 一覧画面での削除処理
   */
  $(".link_student_list_delete").click(function() {
    let ret = confirm("削除してもよろしいでしょうか？");
    if(!ret) {
        return false;
    }
    $("form[id='frm_student_list_delete']").submit();
  });
});
