tinymce.init({
    selector: '#text',
    height: 400,
    plugins: 'powerpaste casechange searchreplace autolink ' +
        'directionality advcode visualblocks visualchars image link media ' +
        'mediaembed codesample table charmap pagebreak nonbreaking anchor ' +
        'tableofcontents insertdatetime advlist lists checklist ' +
        'wordcount tinymcespellchecker editimage help formatpainter ' +
        'permanentpen charmap tinycomments linkchecker emoticons advtable ' +
        'export print autosave',
    toolbar: "undo redo print spellcheckdialog formatpainter | " +
        "fontfamily fontsize | bold italic underline forecolor backcolor | " +
        "link image addcomment showcomments  | alignleft aligncenter alignright alignjustify lineheight |" +
        " checklist bullist numlist indent outdent | removeformat",
    menubar: 'insert',
    images_file_types: "jpg,svg,webp,png",
    block_unsupported_drop: true,
});