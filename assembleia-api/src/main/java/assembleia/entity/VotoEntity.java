package assembleia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "VOTO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VotoEntity {

    private static final String SEQUENCE = "VOTO_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "id", nullable = false, length = 10)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "associado_id", nullable = false)
    private AssociadoEntity associado;

    @Column(name = "voto", nullable = false)
    private Boolean voto;

    @ManyToOne
    @JoinColumn(name = "pauta_id", nullable = false)
    private PautaEntity pauta;
}