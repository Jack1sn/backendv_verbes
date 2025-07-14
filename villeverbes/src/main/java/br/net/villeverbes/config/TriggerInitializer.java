package br.net.villeverbes.config;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class TriggerInitializer {

    private final DataSource dataSource;

    public TriggerInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void createTrigger() {
        String sql = "DROP TRIGGER IF EXISTS trg_atualizar_selo_medalha; " +
                "CREATE TRIGGER trg_atualizar_selo_medalha " +
                "AFTER INSERT ON tb_usuario_jogo " +
                "FOR EACH ROW " +
                "BEGIN " +
                "DECLARE selo_casa_val INT DEFAULT 0; " +
                "DECLARE selo_parque_val INT DEFAULT 0; " +
                "DECLARE selo_universidade_val INT DEFAULT 0; " +
                "DECLARE medalha_val INT DEFAULT 0; " +
                "IF NEW.acertos_casa = 11 THEN SET selo_casa_val = 1; END IF; " +
                "IF NEW.acertos_parque = 11 THEN SET selo_parque_val = 1; END IF; " +
                "IF NEW.acertos_universidade = 11 THEN SET selo_universidade_val = 1; END IF; " +
                "IF selo_casa_val = 1 AND selo_parque_val = 1 AND selo_universidade_val = 1 THEN SET medalha_val = 1; END IF; " +
                "IF EXISTS (SELECT 1 FROM tb_selo_medalha WHERE usuario_jogo_id = NEW.id) THEN " +
                "UPDATE tb_selo_medalha SET " +
                "selo_casa = GREATEST(selo_casa, selo_casa_val), " +
                "selo_parque = GREATEST(selo_parque, selo_parque_val), " +
                "selo_universidade = GREATEST(selo_universidade, selo_universidade_val), " +
                "medalha = CASE WHEN GREATEST(selo_casa, selo_casa_val) = 1 AND GREATEST(selo_parque, selo_parque_val) = 1 AND GREATEST(selo_universidade, selo_universidade_val) = 1 THEN 1 ELSE medalha END " +
                "WHERE usuario_jogo_id = NEW.id; " +
                "ELSE " +
                "INSERT INTO tb_selo_medalha (usuario_jogo_id, usuario_id, personagem, selo_casa, selo_parque, selo_universidade, medalha) " +
                "VALUES (NEW.id, NEW.usuario_id, NEW.personagem, selo_casa_val, selo_parque_val, selo_universidade_val, medalha_val); " +
                "END IF; " +
                "END;";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
