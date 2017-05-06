/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import dao.ActivityDAO;
import dao.AgentDAO;
import dao.ArchiveDAO;
import dao.EntityDAO;
import dao.InstanceDAO;
import dao.UsedDAO;
import dao.WasassociatedwithDAO;
import dao.WasattributedtoDAO;
import dao.WasinformedbyDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import model.Activity;
import model.Agent;
import model.Archive;
import model.Entity;
import model.Instance;
import model.Used;
import model.Wasassociatedwith;
import model.Wasattributedto;
import model.Wasinformedby;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Humberto
 */
@ManagedBean(name = "archiveBean")
@ViewScoped
public class ArchiveBean implements Serializable {

    Archive archive = new Archive();

    List archives = new ArrayList();

    public UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            if (cleanDB() == true) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("DB Clean"));
                if (generateEntitys(file)) {  // generate entitys 
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File: ", file.getFileName() + " is uploaded."));
                    //remove a sessão atual e gerar uma nova:
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("activityBean");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid File. Error in process..."));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failure to clean the BD"));
            }
        }
    }

    //construtor
    public ArchiveBean() {
        archives = new ArchiveDAO().buscarTodas();
        archive = new Archive();
    }

    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new ArchiveDAO().persistir(archive);
        archives = new ArchiveDAO().buscarTodas();
        archive = new Archive();
    }

    public void exclude(ActionEvent actionEvent) {
        new ArchiveDAO().remover(archive);
        archives = new ArchiveDAO().buscarTodas();
        archive = new Archive();
    }

    //getters and setters
    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public List getArchives() {
        return archives;
    }

    public void setArchives(List archives) {
        this.archives = archives;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";

        // pdf.add(Image.getInstance(logo));
    }

    private boolean generateEntitys(UploadedFile file) {
        try {
            String[] objetcs;
            InputStreamReader isr = new InputStreamReader(file.getInputstream());
            BufferedReader br = new BufferedReader(isr);

            String xFile = br.readLine();
            int nLines = 1;
            while (xFile != null) {
                if (nLines > 1) {
                    objetcs = xFile.split(";");

                    if (objetcs.length == 12) { // ignore objects with fewer than four parameters
                        persistObjects(objetcs);
                    }
                }

                nLines++;
                xFile = br.readLine();
            }

            Archive arq = new Archive();
            arq.setName(file.getFileName());
            Calendar cal = Calendar.getInstance();
            arq.setTimeImport(cal.getTime());

            new ArchiveDAO().persistir(arq);

            return true;
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void persistObjects(String[] objetcs) throws ParseException {
        System.out.println("Iniciando persistencia dos objetos.");
        Activity activity = new Activity();
        activity.setName(objetcs[1]);
        activity.setIdProcessInstance(parseInt(objetcs[0]));
        activity.setPriority(objetcs[3]);
        activity.setTypeActivity(objetcs[2]);
        // Start e End Dates
        Date startDate;
        Date endDate;
        //SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone("BRT"));
        startDate = objetcs[4].equals("-") ? null : df.parse(objetcs[4]);
        endDate = objetcs[5].equals("-") ? null : df.parse(objetcs[5]);
        activity.setStartTime(startDate);
        activity.setEndTime(endDate);
        activity = ActivityDAO.getInstance().persistir(activity);

        Instance instance = new Instance();
        Instance resultinstance = InstanceDAO.getInstance().buscar(activity.getIdProcessInstance());
        if (resultinstance == null) {
            instance.setIdProcessInstance(activity.getIdProcessInstance());
            instance.setStartTime(startDate);
            instance.setEndTime(endDate);
            InstanceDAO.getInstance().persistir(instance);
        } else {
            instance.setIdProcessInstance(resultinstance.getIdProcessInstance());
            instance.setStartTime(resultinstance.getStartTime());
            instance.setEndTime(endDate);
            InstanceDAO.getInstance().persistir(instance);
        }

        Agent agent = new Agent();
        Agent result = AgentDAO.getInstance().buscar(objetcs[6]);
        if (result == null) {
            agent.setName(objetcs[6]);
            if (objetcs[7] == null) {
                agent.setTypeAgent("Person");
            } else {
                agent.setTypeAgent(objetcs[7]);
            }
            agent = AgentDAO.getInstance().persistir(agent);
        } else {
            agent = AgentDAO.getInstance().buscar(objetcs[6]);
        }

        if (!objetcs[8].equals("-")) {
            Entity entity = new Entity();
            Wasattributedto wat = new Wasattributedto();
            Wasassociatedwith waw = new Wasassociatedwith();

            Entity resultentity = EntityDAO.getInstance().buscar(objetcs[8]);
            if (resultentity == null) {
                entity.setName(objetcs[8]);
                entity.setTypeEntity("Module");
                entity = EntityDAO.getInstance().persistir(entity);
            } else {
                entity = EntityDAO.getInstance().buscar(objetcs[8]);
            }

            wat.setAgentidAgent(agent);
            wat.setEntityidEntity(entity);
            WasattributedtoDAO.getInstance().persistir(wat);

            waw.setActivityidActivity(activity);
            waw.setAgentidAgent(agent);
            waw.setEntityidEntityPlan(entity);
            WasassociatedwithDAO.getInstance().persistir(waw);

            Used used = new Used();
            used.setEntityidEntity(entity);
            used.setActivityidActivity(activity);
            UsedDAO.getInstance().persistir(used);
        }

        if (!objetcs[9].equals("-")) {
            Entity entity = new Entity();

            Entity resultentity = EntityDAO.getInstance().buscar(objetcs[9]);
            if (resultentity == null) {
                entity.setName(objetcs[9]);
                entity.setTypeEntity("Component");
                entity = EntityDAO.getInstance().persistir(entity);
            } else {
                entity = EntityDAO.getInstance().buscar(objetcs[9]);
            }

            Wasattributedto wat = new Wasattributedto();
            wat.setAgentidAgent(agent);
            wat.setEntityidEntity(entity);
            WasattributedtoDAO.getInstance().persistir(wat);

            Wasassociatedwith waw = new Wasassociatedwith();
            waw.setActivityidActivity(activity);
            waw.setAgentidAgent(agent);
            waw.setEntityidEntityPlan(entity);
            WasassociatedwithDAO.getInstance().persistir(waw);

            Used used = new Used();
            used.setEntityidEntity(entity);
            used.setActivityidActivity(activity);
            UsedDAO.getInstance().persistir(used);
        }

        if (!objetcs[10].equals("-")) {
            Entity entity = new Entity();

            entity.setName(objetcs[10]);
            entity.setTypeEntity("Version");
            entity = EntityDAO.getInstance().persistir(entity);

            Wasattributedto wat = new Wasattributedto();
            wat.setAgentidAgent(agent);
            wat.setEntityidEntity(entity);
            WasattributedtoDAO.getInstance().persistir(wat);

            Wasassociatedwith waw = new Wasassociatedwith();
            waw.setActivityidActivity(activity);
            waw.setAgentidAgent(agent);
            waw.setEntityidEntityPlan(entity);
            WasassociatedwithDAO.getInstance().persistir(waw);

            Used used = new Used();
            used.setEntityidEntity(entity);
            used.setActivityidActivity(activity);
            UsedDAO.getInstance().persistir(used);
        }

        if (!objetcs[11].equals("-")) {
            int id = parseInt(objetcs[11]);
            Activity act = ActivityDAO.getInstance().buscar(id);
            if (act != null) {
                Wasinformedby wib = new Wasinformedby();
                wib.setActivityidActivityInformant(act);
                wib.setActivityidActivityInformed(activity);
                WasinformedbyDAO.getInstance().persistir(wib);
            }
        }
    }

    private boolean cleanDB() {
        System.out.println("Iniciando processo de limpeza");
        WasinformedbyDAO.getInstance().removeAll();
        WasattributedtoDAO.getInstance().removeAll();
        UsedDAO.getInstance().removeAll();
        WasassociatedwithDAO.getInstance().removeAll();
        ActivityDAO.getInstance().removeAll();
        AgentDAO.getInstance().removeAll();
        EntityDAO.getInstance().removeAll();
        InstanceDAO.getInstance().removeAll();

        return true;

    }
}
