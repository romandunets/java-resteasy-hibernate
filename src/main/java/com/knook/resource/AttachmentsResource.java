package com.knook.resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.knook.dao.AttachmentDao;
import com.knook.dao.NoteDao;
import com.knook.model.Attachment;
import com.knook.model.Note;
import com.knook.serializer.AttachmentSerializer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("attachments")
@Produces(MediaType.APPLICATION_JSON)
public class AttachmentsResource {

    private final String UPLOAD_DIR = "/tmp/";

    private GsonBuilder builder = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(Attachment.class, new AttachmentSerializer());;
    private Gson gson = builder.create();

    private AttachmentDao attachmentDao = new AttachmentDao();
    private NoteDao noteDao = new NoteDao();

    @GET
    @Path("/{note_id}")
    public Response list(@PathParam("note_id") Long noteId) {
        Note note = noteDao.get(noteId);

        if (note != null) {
            String response = gson.toJson(note.getAttachments());
            return Response.ok(response).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/upload")
    @Consumes("multipart/form-data")
    public Response uploadFile(MultipartFormDataInput input, @HeaderParam("note_id") Long note_id) throws IOException {
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("attachment");
        String filename = UPLOAD_DIR + getRandomFilename();

        for (InputPart inputPart : inputParts) {
            try {
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] bytes = IOUtils.toByteArray(inputStream);
                writeFile(bytes, filename);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        Note note = noteDao.get(note_id);
        Attachment attachment = new Attachment(filename, note);

        if (note != null && attachmentDao.create(attachment)) {
            return Response.status(Response.Status.OK).build();
        }
        else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    private void writeFile(byte[] content, String filename) throws IOException {
        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();
    }

    private String getRandomFilename() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhmmssSSS");
        String timestamp = sdf.format(new Date());

        Random random = new Random();
        Integer randomNumber = random.nextInt(900) + 100;

        String filename = String.format("%s%s", timestamp, randomNumber.toString());
        return filename;
    }

}
