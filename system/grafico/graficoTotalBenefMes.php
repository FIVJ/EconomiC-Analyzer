<?php

require_once "../vendor/autoload.php";
require_once "../db/connection.php";
require_once "../reports/mem_image.php";

session_start();
        if((!isset ($_SESSION['login']) == true) and (!isset ($_SESSION['password']) == true))
        {
            unset($_SESSION['login']);
            unset($_SESSION['password']);
      header('location: ../login.php');
        }else{

#Instancia o objeto e setando o tamanho do grafico na tela
$grafico = new \PHPlot(1200, 300);
#Indicamos o títul do gráfico e o título dos dados no eixo X e Y do mesmo
$grafico->SetTitle(utf8_decode("Beneficiaries by Month and Year"));
//$grafico->SetTitle("Beneficiários por Mês e Ano");
$grafico->SetXTitle(utf8_decode("Month and Year"));

$grafico->SetYTitle(utf8_decode("Total Beneficiaries"));

$query = "SELECT count(tb_beneficiaries_id_beneficiaries )as qtde, int_month as mes, int_year as ano
          FROM tb_payments group by int_month, int_year order by int_year asc, int_month asc;";
$statement = $pdo->prepare($query);

$statement->execute();
$rs = $statement->fetchAll(PDO::FETCH_ASSOC);
foreach ($rs as $value) {
    $resultado[] = $value;
}
$data = array();
if (isset($resultado)) {
    foreach ($resultado as $r) {
        $data[] = [$r['ano'] . '/' . $r['mes'], $r['qtde']];
    }
} else {
    $data[] = [null, null];
}
//$grafico->SetDefaultTTFont('assets/fonts/Timeless.ttf');
$grafico->SetDataValues($data);

$grafico->SetPlotType("lines");
#Exibimos o gráfico

if (isset($_GET['print']) && $_GET['print'] == 'TRUE') {
    $grafico->SetPrintImage(FALSE);
}

$grafico->DrawGraph();

$pdf = new PDF_MemImage();
$pdf->AddPage();
$pdf->GDImage($grafico->img, 30, 20, 240);
$pdf->Output();
        }
