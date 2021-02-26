$(document).ready(function () {

$('#fileImage').change(function(){
showImageThumbnail(this);
});


function showImageThumbnail(fileInput){
file = fileInput.files[0];

 if (file!= null) {

        reader = new FileReader();
        reader.onload = function(e){
                            $('#thumbnail').attr('src',e.target.result);
                                   };
        reader.readAsDataURL(file);
}
else {
                            $('#thumbnail').removeAttr('src');
     }

};
});