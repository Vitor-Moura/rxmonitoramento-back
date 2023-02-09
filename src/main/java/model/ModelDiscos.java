/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.github.britooo.looca.api.group.discos.DiscosGroup;
import com.github.britooo.looca.api.group.discos.Volume;

/**
 *
 * @author raylane
 */
public class ModelDiscos extends Model {

    private Double tamanhoTotal;
    private DiscosGroup discosGroup;

    public ModelDiscos() {
        super("Senhor(a), gerente ! "
                + "Há máquinas no setor de atendimento que estão chegando no seu limite de armazenamento! :astonished: :computer:");
        this.discosGroup = new DiscosGroup();
        this.tamanhoTotal = (Double.valueOf(discosGroup.getVolumes()
                .get(0)
                .getTotal())) / 1000000000;
    }

    public Double getTamanhoTotal() {
        return tamanhoTotal;
    }

    public Double getDisponivel() {
        Double disponivel = Double.valueOf(discosGroup.getVolumes().get(0).getDisponivel());
        return disponivel /= 1000000000;
    }

    public Double getEmUso() {
        Double emUso = this.getTamanhoTotal() - this.getDisponivel();
        Double percentDisco = (emUso * 100) / tamanhoTotal;
        notifyIfNecessary(percentDisco);
        return emUso;
    }

}
