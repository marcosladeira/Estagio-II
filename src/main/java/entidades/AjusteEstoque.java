 package entidades;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;
        import javax.persistence.CascadeType;
        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.FetchType;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.OneToMany;
        import javax.persistence.Temporal;
        import javax.persistence.TemporalType;

/**
 *
 * @author marcos-ladeira
 */
        @Entity
        public class AjusteEstoque implements Serializable, ClassePai {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        private Date dataAjuste;
        @Column(length = 255, nullable = false)
        private String motivo;
        @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "ajusteEstoque")
        private List<ItensAjusteEstoque> itensAjusteEstoque;

        public AjusteEstoque() {
        this.dataAjuste = new Date();
        this.itensAjusteEstoque = new ArrayList<ItensAjusteEstoque>();
        }

        public Date getDataAjuste() {
        return dataAjuste;
        }

        public void setDataAjuste(Date dataAjuste) {
        this.dataAjuste = dataAjuste;
        }

        public String getMotivo() {
        return motivo;
        }

        public void setMotivo(String motivo) {
        this.motivo = motivo;
        }

        public List<ItensAjusteEstoque> getItensAjusteEstoque() {
        return itensAjusteEstoque;
        }

        public void setItensAjusteEstoque(List<ItensAjusteEstoque> itensAjusteEstoque) {
        this.itensAjusteEstoque = itensAjusteEstoque;
        }

        @Override
        public Long getId() {
        return id;
        }

        public void setId(Long id) {
        this.id = id;
        }

        @Override
        public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
        }

        @Override
        public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AjusteEstoque)) {
        return false;
        }
        AjusteEstoque other = (AjusteEstoque) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
        return false;
        }
        return true;
        }

        @Override
        public String toString() {
        return "entidades.AjusteEstoque[ id=" + id + " ]";
        }

        }
