/*
 * To change this license header, choose License Headers in Project Properties.
jajab * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import util.ReportsUtil;

/**
 *
 * @author marcos-ladeira
 */
@Named
@RequestScoped
public class RelatorioControle implements Serializable {

    @Inject
    private EntityManager em;

    private Date dataInicio;
    private Date dataFim;
    @Inject
    private LoginControle loginControle;

    public void gerarRelatorioEstados() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relEstado.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }
    public void gerarRelatorioCidades() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relCidade.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }


    public void gerarRelatorioFormasPagamento() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relFormaPagamento.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioGruposProduto() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relGrupoProduto.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioContasBancarias() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relContaBancaria.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioMesas() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relMesa.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioPessoasFisicas() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relPessoaFisica.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioPessoasJuridicas() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relPessoaJuridica.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioFuncionarios() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relFuncionario.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioPerfis() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relPerfil.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioProdutos() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relProduto.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioUsuarios() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relUsuario.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioFidelidadeClientes() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relFidelidadeClientes.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioProdutosMaisVendidos() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relProdutosMaisVendidos.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioProdutosMenosVendidos() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relProdutosMenosVendidos.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioProdutosMaisComprados() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relProdutosMaisComprados.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioProdutosMenosComprados() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relProdutosMenosComprados.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioProdutosAbaixoEstoqueMinimo() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relProdutosAbaixoEstoqueMinimo.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioProdutosMenorEstoque() {
        try {
            ReportsUtil util = new ReportsUtil();
            util.gerarRelatorioPDF(null, "WEB-INF/reports/relProdutosMenorEstoque.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioCompras() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relCompras.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioVendas() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relVendas.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioContasPagar() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relContasPagar.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioContasReceber() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            param.put("dataFim", dataFim);
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relContasReceber.jasper", getConnection());
        } catch (IOException | JRException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }

    public void gerarRelatorioMovimentacaoDiaria() {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd ");
            ReportsUtil util = new ReportsUtil();
            Map<String, Object> param = new HashMap<>();
            param.put("dataInicio", dataInicio);
            System.out.println(fmt.format(dataInicio));
            util.gerarRelatorioPDF(param, "WEB-INF/reports/relMovimentacaoDiaria.jasper", getConnection());
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
    }


    public Connection getConnection() throws SQLException {
        Session session = em.unwrap(Session.class);
        SessionFactoryImplementor sfi = (SessionFactoryImplementor) session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        Connection connection = cp.getConnection();
        return connection;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

}
