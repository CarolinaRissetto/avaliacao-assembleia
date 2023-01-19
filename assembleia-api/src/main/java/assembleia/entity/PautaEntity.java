package assembleia.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "PAUTA")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaEntity {

    private static final String SEQUENCE = "PAUTA_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, allocationSize = 1)
    @Column(name = "id", nullable = false, length = 10)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String descricao;

    @Column(name = "data_hora_abertura_sessao", length = 50)
    private LocalDateTime dataHoraAberturaSessao;

    @Column(name = "tempo_duracao", length = 50)
    private Integer tempoDuracao;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="pauta")
    @Column(name = "votos", length = 50)
    private List<VotoEntity> votos;
}