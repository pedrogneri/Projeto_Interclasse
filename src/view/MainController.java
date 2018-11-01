package view;
import interclasse.Grupo;
import interclasse.Salas;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private Salas primeiros = new Salas();
    private Salas segundos = new Salas();
    private Salas terceiros = new Salas();

    private Grupo grupos = new Grupo();

    private List<String> salasPrimeiros = new ArrayList<>(6);
    private List<String> salasSegundos = new ArrayList<>(6);
    private List<String> salasTerceiros = new ArrayList<>(6);

    //Panel
    private Home home;
    //Button
    private JButton btnNext;
    private JButton btnPrev;
    private JButton salvarButton;
    private JButton limparButton;
    //Label
    private JLabel lbEsporte;
    private JLabel lbArray;
    private JLabel lbArray2;
    //Panel
    private JPanel panelPrimeiros;
    private JPanel panelSegundos;
    private JPanel panelTerceiros;

    public MainController() {
        initComponents();
        initListeners();
    }

    private void initComponents() {
        home = new Home();

        //Button
        btnNext = home.getBtnNext();
        btnPrev = home.getBtnPrev();
        salvarButton = home.getSalvarButton();
        limparButton = home.getLimparButton();
        //Label
        lbEsporte = home.getLbEsporte();
        lbArray = home.getLbArray();
        lbArray2 = home.getLbArray2();
        //Panel
        panelPrimeiros = home.getPanelPrimeiros();
        panelSegundos = home.getPanelSegundos();
        panelTerceiros = home.getPanelTerceiros();
    }

    private void initListeners() {
        //Button
        btnNext.addActionListener(new BtnNextListener());
        btnPrev.addActionListener(new BtnPrevListener());
        salvarButton.addActionListener(new salvarButton());
        limparButton.addActionListener(new limparButton());
        //Checkbox
        for(int x=0; x < panelPrimeiros.getComponentCount(); x++) {
            JCheckBox cbPrimeiros = (JCheckBox) panelPrimeiros.getComponent(x);
            JCheckBox cbSegundos = (JCheckBox) panelSegundos.getComponent(x);
            JCheckBox cbTerceiros = (JCheckBox) panelTerceiros.getComponent(x);

            cbPrimeiros.addItemListener(new primeirosListener());
            cbSegundos.addItemListener(new segundosListener());
            cbTerceiros.addItemListener(new terceirosListener());
        }

    }

    public void showView() {
        home.setVisible(true);
    }

    private class BtnNextListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(lbEsporte.getText()){
                case "Futsal":
                    lbEsporte.setText("Basquete");
                    break;
                case "Basquete":
                    lbEsporte.setText("Handebol");
                    break;
                case "Handebol":
                    lbEsporte.setText("Vôlei");
                    break;
                default:
                   lbEsporte.setText("Futsal");
                   break;
            }
        }
    }

    private class BtnPrevListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(lbEsporte.getText()){
                case "Futsal":
                    lbEsporte.setText("Vôlei");
                    break;
                case "Vôlei":
                    lbEsporte.setText("Handebol");
                    break;
                case "Handebol":
                    lbEsporte.setText("Basquete");
                    break;
                default:
                    lbEsporte.setText("Futsal");
                    break;
            }
        }
    }

    private class salvarButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            montarGruposAno(primeiros, salasPrimeiros);
            montarGruposAno(segundos, salasSegundos);
            montarGruposAno(terceiros, salasTerceiros);

            grupos.montarGrupos(primeiros,segundos,terceiros);

            lbArray.setText(String.valueOf(
                    grupos.getListaGrupos().get(0) + " " + grupos.getListaGrupos().get(1) +
                    " " + grupos.getListaGrupos().get(2)));
            lbArray2.setText(String.valueOf(
                    grupos.getListaGrupos().get(3) + " " + grupos.getListaGrupos().get(4) +
                    " " + grupos.getListaGrupos().get(5)));

            lbArray.setVisible(true);
            lbArray2.setVisible(true);
        }

        public void montarGruposAno(Salas salas, List<String> participantes) {
            salas.setSalasParticipantes(participantes);
            salas.montadorGrupos();
        }
    }

    private class limparButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int x=0; x < panelPrimeiros.getComponentCount(); x++) {
                JCheckBox cbPrimeiros = (JCheckBox) panelPrimeiros.getComponent(x);
                JCheckBox cbSegundos = (JCheckBox) panelSegundos.getComponent(x);
                JCheckBox cbTerceiros = (JCheckBox) panelTerceiros.getComponent(x);

                cbPrimeiros.setSelected(false);
                cbSegundos.setSelected(false);
                cbTerceiros.setSelected(false);
            }

            salasPrimeiros.clear();
            salasSegundos.clear();
            salasTerceiros.clear();

            lbArray.setVisible(false);
            lbArray2.setVisible(false);
        }
    }

    private class primeirosListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox cb = (JCheckBox) e.getItemSelectable();
            String texto = cb.getText();

            if(cb.isSelected())
                salasPrimeiros.add(texto);
            else
                salasPrimeiros.remove(texto);
        }
    }
    private class segundosListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox cb = (JCheckBox) e.getItemSelectable();
            String texto = cb.getText();

            if(cb.isSelected())
                salasSegundos.add(texto);
            else
                salasSegundos.remove(texto);
        }
    }
    private class terceirosListener implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox cb = (JCheckBox) e.getItemSelectable();
            String texto = cb.getText();

            if(cb.isSelected())
                salasTerceiros.add(texto);
            else
                salasTerceiros.remove(texto);
        }
    }

}