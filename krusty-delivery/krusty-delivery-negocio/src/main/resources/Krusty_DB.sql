mvn archetype:generate -DgroupId=br.com.walmart -DartifactId=krusty-delivery -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

********************************************************************************************
***************** Configuracoes para Glassfish Application Server **************************

---
--- Configuracao do Data Source
---
<jdbc-resource enabled="true" jndi-name="jdbc/krustyds" object-type="user" pool-name="OracleConnectionPool">
  <description>Conexão do Krusty-Delivery</description>
</jdbc-resource>

---
--- Configuracao do Pool de Conexao
---
<jdbc-connection-pool allow-non-component-callers="true" associate-with-thread="false" connection-creation-retry-attempts="0" connection-creation-retry-interval-in-seconds="10" connection-leak-reclaim="false" connection-leak-timeout-in-seconds="0" connection-validation-method="auto-commit" datasource-classname="oracle.jdbc.xa.client.OracleXADataSource" fail-all-connections="false" idle-timeout-in-seconds="300" is-connection-validation-required="true" is-isolation-level-guaranteed="true" lazy-connection-association="false" lazy-connection-enlistment="false" match-connections="false" max-connection-usage-count="0" max-pool-size="24" max-wait-time-in-millis="60000" name="OracleConnectionPool" non-transactional-connections="false" pool-resize-quantity="2" res-type="javax.sql.XADataSource" statement-timeout-in-seconds="-1" steady-pool-size="4" validate-atmost-once-period-in-seconds="0" wrap-jdbc-objects="false">
  <property name="xa-driver-does-not-support-non-tx-operations" value="true"></property>
  <property name="oracle-xa-recovery-workaround" value="true"></property>
  <property name="URL" value="jdbc:oracle:thin:@marcosvinicius:1521:MARCOS"></property>
  <property name="user" value="KRUSTY"></property>
  <property name="password" value="KRUSTY"></property>
</jdbc-connection-pool>

<servers>
	<server>
		<resource-ref ref="jdbc/krustyds" enabled="true" />
	</server>
</servers>

--- configuracao para correcao de erro no application server
<jvm-options>-Dorg.omg.CORBA.ORBSingletonClass=com.sun.corba.se.impl.orb.ORBSingleton</jvm-options>
---

********************************************************************************************

***************** Oracle Data Base *********************************************************
sqlplus system/MARCOS@MARCOS

DROP USER KRUSTY CASCADE
/
CREATE USER KRUSTY IDENTIFIED BY KRUSTY
    DEFAULT TABLESPACE ERP
    TEMPORARY TABLESPACE TEMP
    PROFILE DEFAULT
    ACCOUNT UNLOCK
/
GRANT CONNECT TO KRUSTY
/
GRANT DBA TO KRUSTY
/
GRANT RESOURCE TO KRUSTY
/
GRANT UNLIMITED TABLESPACE TO KRUSTY
/
ALTER USER KRUSTY DEFAULT ROLE CONNECT, RESOURCE, DBA
/
GRANT CREATE TABLE TO KRUSTY
/
EXIT
/

CREATE TABLE PEDIDO
  (
    PEDIDO_ID NUMBER(10, 0) NOT NULL ,
    DATAPEDIDO DATE NOT NULL ,
    NOMECLIENTE  VARCHAR2(60) ,
    MESA         NUMBER(10, 0) NOT NULL ,
    NUMEROPEDIDO NUMBER(10, 0) NOT NULL ,
    TOTALPEDIDO  NUMBER(10, 2) NOT NULL ,
    STATUS       VARCHAR2(30) NOT NULL ,
    CONSTRAINT PEDIDO_PK PRIMARY KEY ( PEDIDO_ID ) ENABLE
  );
 
CREATE INDEX PEDIDO_INDEX1 ON PEDIDO (DATAPEDIDO);
CREATE INDEX PEDIDO_INDEX2 ON PEDIDO (NUMEROPEDIDO);
CREATE INDEX PEDIDO_INDEX3 ON PEDIDO (STATUS); 
  
COMMENT ON COLUMN PEDIDO.PEDIDO_ID IS  'Identificador do Pedido ( Chave )';
COMMENT ON COLUMN PEDIDO.DATAPEDIDO IS 'Data do Pedido';
COMMENT ON COLUMN PEDIDO.NOMECLIENTE IS  'Nome do Cliente';
COMMENT ON COLUMN PEDIDO.MESA IS  'Número da Mesa';
COMMENT ON COLUMN PEDIDO.NUMEROPEDIDO IS 'Número do Pedido';
COMMENT ON COLUMN PEDIDO.TOTALPEDIDO IS 'Valor total do Pedido';
COMMENT ON COLUMN PEDIDO.STATUS IS 'Estatus do Pedido (Novo, Em atendimento, Finalizado, Cancelado, Entregue)';


CREATE TABLE PEDIDOLINHA
  (
    PEDIDOLINHA_ID NUMBER(10) NOT NULL ,
    PRODUTO_ID     NUMBER(10) NOT NULL ,
    NUMEROLINHA    NUMBER(10) NOT NULL ,
    VALOR          NUMBER(10, 1) NOT NULL
  );

ALTER TABLE PEDIDOLINHA ADD CONSTRAINT PEDIDOLINHA_PK PRIMARY KEY 
( PEDIDOLINHA_ID ) ENABLE;
CREATE INDEX PEDIDOLINHA_INDEX1 ON PEDIDOLINHA (PRODUTO_ID);
CREATE INDEX PEDIDOLINHA_INDEX2 ON PEDIDOLINHA (NUMEROLINHA);
CREATE INDEX PEDIDOLINHA_INDEX3 ON PEDIDOLINHA (PEDIDO_ID);

COMMENT ON COLUMN PEDIDOLINHA.PEDIDOLINHA_ID IS 'Identificador da LInha do Pedido ( chave )';
COMMENT ON COLUMN PEDIDOLINHA.PRODUTO_ID IS 'Chave estrangeira com a Tabela de Produto';
COMMENT ON COLUMN PEDIDOLINHA.NUMEROLINHA IS 'Número sequencial da linha do Pedido';
COMMENT ON COLUMN PEDIDOLINHA.VALOR IS 'Valor da Linha do Pedido';

CREATE TABLE PRODUTO
  (
    PRODUTO_ID NUMBER(10) NOT NULL ,
    CODIGO     NUMBER(10) NOT NULL ,
    NOME       VARCHAR2(60) NOT NULL ,
    PRECO      NUMBER(10, 2) NOT NULL ,
    CONSTRAINT PRODUTO_PK PRIMARY KEY ( PRODUTO_ID ) ENABLE
  );

COMMENT ON COLUMN PRODUTO.PRODUTO_ID IS 'Identificador do Produto ( Chave )';
COMMENT ON COLUMN PRODUTO.CODIGO IS 'Codigo do Produto';
COMMENT ON COLUMN PRODUTO.NOME IS 'Descrição do Produto';
COMMENT ON COLUMN PRODUTO.PRECO IS 'Valor do Produto';

CREATE INDEX PRODUTO_INDEX1 ON PRODUTO (CODIGO);
CREATE INDEX PRODUTO_INDEX2 ON PRODUTO (NOME);

ALTER TABLE PEDIDOLINHA ADD (PEDIDO_ID NUMBER(10) NOT NULL);
ALTER TABLE PEDIDOLINHA ADD CONSTRAINT PEDIDOLINHA_PEDIDO_FK1 FOREIGN KEY
(  PEDIDO_ID ) REFERENCES PEDIDO ( PEDIDO_ID ) ENABLE;
ALTER TABLE PEDIDOLINHA ADD CONSTRAINT PEDIDOLINHA_PRODUT0_FK1 FOREIGN KEY
(  PRODUTO_ID  ) REFERENCES PRODUTO( PRODUTO_ID ) ENABLE;

COMMENT ON COLUMN PEDIDOLINHA.PEDIDO_ID IS 'Chave com a tabela de Pedidos';

INSERT INTO "KRUSTY"."PEDIDO" (PEDIDO_ID, DATAPEDIDO, NOMECLIENTE, MESA, NUMEROPEDIDO, TOTALPEDIDO, STATUS, ID, NOME_CLIENTE, DATA_HORA_ATENDIMENTO, DATA_PEDIDO, EMAIL, TELEFONE) VALUES ('123', TO_DATE('05/06/14', 'DD/MM/RR'), 'MARCOS VINICIUS', '1', '1', '10', 'Novo', '123', 'MARCOS VINICIUS', TO_TIMESTAMP('05/06/14 11:00:22,599000000', 'DD/MM/RR HH24:MI:SSXFF'), TO_TIMESTAMP('05/06/14 11:00:30,826000000', 'DD/MM/RR HH24:MI:SSXFF'), 'vinicius.marcs@gmail.com', '12331231')
INSERT INTO "KRUSTY"."PEDIDOLINHA" (PEDIDOLINHA_ID, PRODUTO_ID, NUMEROLINHA, VALOR, PEDIDO_ID, CODIGO, NOME, PRECO, QUANTIDADE, DESCRICAOPRODUTO) VALUES ('123', '123', '1', '10', '123', '1', 'Pizza', '10', '1', 'Pizza')


********************************************************************************************	