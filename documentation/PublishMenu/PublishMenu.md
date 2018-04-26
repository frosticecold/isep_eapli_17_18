=================
Análise
=================
Reunião com cliente em 06/04/2018

As ementas a serem publicadas serão todas as que apresentam o estado de "InProgress".
Sugestão: Publicar as ementas que estão a 72h do prazo.

Reunião com cliente em 12/04/2018

Serão selecionadas as ementas a publicar baseado numa lista ordenada descendentemente por importância (diferença entre a data de inicio da ementa e a data em que se está a fazer a publicação).

=================
Regras de Negócio
=================
Só serão publicadas as ementas válidas.
Só serão publicadas as ementas que estão no estado "InProgress".
São consideradas ementas válidas todas aquelas que sejam compostas por uma refeição de cada tipo (carne, peixe e vegetariana).
As ementas a considerar como publicadas tem um sub estado (tendo em conta a data) relativo à sua importância (critico e não-critico).

=================
Plano de Testes
=================
1. Bootstrap
2. Login como chef
3. Selecionar Menus (3.)
4. Selecionar "Publish Menus"
5. Selecionar o tipo de menus a Publicar
6. ???
7. Profit
