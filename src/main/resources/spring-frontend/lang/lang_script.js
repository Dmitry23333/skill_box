src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.jc"
type="text/javascript">
    $(document).ready(function(){
    $("#locales").change(function (){
        const selectedOption = $("#locales").val();
        if (selectedOption !==''){
            window.location.replace('?lang=' +selectedOption);
        }
    });
});
