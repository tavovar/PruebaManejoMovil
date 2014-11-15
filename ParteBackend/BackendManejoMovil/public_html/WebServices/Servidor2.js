/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var multiparty = require('multiparty')
        , http = require('http')
        , util = require('util')
        , fs = require('fs')
        , uuid = require('node-uuid');

http.createServer(function (req, res) {
    if (req.url === '/upload' && req.method === 'POST') {
        var size = '';
        var file_name = '';
        var destination_path = '';

        var form = new multiparty.Form();

        form.parse(req, function (err, fields, files) {
            console.log(files.displayImage);
            console.log(fields.AccionCorrecta[0]);
            
            var mAccionCorrecta = fields.AccionCorrecta[0];
            var file = files.displayImage[0];
            
            var temporal_path = file.path;
            var destination_path = 'C:/Users/Ney Rojas/Pictures/' + file.originalFilename;
            var input_stream = fs.createReadStream(temporal_path);
            var output_stream = fs.createWriteStream(destination_path);
            input_stream.pipe(output_stream);

            input_stream.on('end', function () {
                console.log('Uploaded : ' + destination_path);
            });
            
        });

        form.on('error', function (err) {
            console.log('Error parsing form: ' + err.stack);
        });

       return;
    }

    // show a file upload form
    res.writeHead(200, {'content-type': 'text/html'});
    res.end(
            '<form action="/upload" enctype="multipart/form-data" method="post">' +
            '<input type="text" name="title"><br>' +
            '<input type="file" name="upload" multiple="multiple"><br>' +
            '<input type="submit" value="Upload">' +
            '</form>'
            );
}).listen(8080);