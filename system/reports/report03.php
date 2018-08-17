<?php

ini_set('display_errors', 1);

require_once  "../vendor/autoload.php";
require_once "../dao/reportDAO.php";

session_start();
        if((!isset ($_SESSION['login']) == true) and (!isset ($_SESSION['password']) == true))
        {
            unset($_SESSION['login']);
            unset($_SESSION['password']);
      header('location: ../login.php');
        }else{

$dao = new reportDAO();

$listObjs = $dao->report03();
$dia = $dao ->dateNow();
$hr = $dao ->hourNow();

        $html = "<table border='2' cellspacing='3' cellpadding='5' >";
$html .= "<tr>
            <th>ID PAGAMENTO</th>
            <th>CIDADE</th>
            <th>FUNÇÃO</th>
            <th>SUB-FUNCAO</th>
            <th>PROGRAMA</th>
            <th>AÇÃO</th>
            <th>BENEFICIARIES</th>
            <th>NIS</th>
            <th>ARQUIVO</th>
        </tr>";
foreach ($listObjs as $var):
    $html.= "<tr>
                <td>$var->a1</td>
                <td>$var->a2</td>
                <td>$var->a3</td>
                <td>$var->a4</td>
                <td>$var->a5</td>
                <td>$var->a6</td>
                <td>$var->a7</td>
                <td>$var->a8</td>
                <td>$var->a9</td>
          </tr>";
endforeach;
$html .= "</table>";

$mpdf=new \Mpdf\Mpdf();
//$mpdf=new mPDF();
$mpdf->SetCreator('PDF_CREATOR');
$mpdf->SetAuthor('Tassio Sirqueira');
$mpdf->SetTitle('Relatório PDF com a lista de os pagamentos, incluindo seus respectivos dados');
$mpdf->SetSubject('EconomiC Analyzer');
$mpdf->SetKeywords('TCPDF, ECA');
$mpdf->SetDisplayMode('fullpage');
$mpdf->nbpgPrefix = ' de ';
$mpdf->setFooter("Relatório gerado no dia {$dia} às {$hr} - Página {PAGENO}{nbpg}");
$mpdf->WriteHTML($html);
$mpdf->Output('economicAnalyzer.pdf','I');

exit;
        
        
}
