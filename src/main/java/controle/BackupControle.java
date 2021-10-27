/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author marcos-ladeira
 */
@Named
@SessionScoped
public class BackupControle implements Serializable {

    public void realizaBackup() {
        String snh = "junior457";
        String banco = "unibar";
        File diretorio = new File("/home/marcos-ladeira/backup");
        if (!diretorio.isDirectory())
            new File("/home/marcos-ladeira/backup").mkdir();
        try {
            String comando = "/usr/bin/mysqldump";
            ProcessBuilder pb = new ProcessBuilder(comando, "--user=root", "--password=" + snh, banco,
                    "--result-file=/home/marcos-ladeira/backup_" + banco + ".sql");
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Backup realizado com sucesso!"));
    }

    public void realizaRestore(){
        String snh ="junior457";
        String banco = "unibar";

        try{
            String comando = "/usr/bin/mysql";
            ProcessBuilder pb = new ProcessBuilder(comando, "--user=root", "--password=" + snh, banco,
                    "--execute=source /home/marcos-ladeira/backup_" + banco + ".sql");
            pb.start();
        } catch (IOException e){
            e.printStackTrace();
        }
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage("Backup restaurado com sucesso!"));
    }
}
