                /*
                 * To change this license header, choose License Headers in Project Properties.
                 * To change this template file, choose Tools | Templates
                 * and open the template in the editor.
                 */
                package entidades;

                import java.io.Serializable;
                import javax.persistence.Entity;


                /**
                 *
                 * @author marcos-ladeira
                 */
                @Entity
                public class PessoaJuridica extends Pessoa implements Serializable {
                    private String razaoSocial;
                    private String ie;
                    private String cnpj;

                    public String getRazaoSocial() {
                        return razaoSocial;
                    }

                    public void setRazaoSocial(String razaoSocial) {
                        this.razaoSocial = razaoSocial;
                    }

                    public String getCnpj() {
                        return cnpj;
                    }

                    public void setCnpj(String cnpj) {
                        this.cnpj = cnpj;
                    }


                    public String getIe() {
                        return ie;
                    }

                    public void setIe(String ie) {
                        this.ie = ie;
                    }

                }
