/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mapa_prog2;

/**
 *
 * @author Thonny
 */
import com.mycompany.mapa_prog2.modelo.Projeto;
import com.mycompany.mapa_prog2.exceptions.DadoConsultadoException;
import com.mycompany.mapa_prog2.controle.ProjetoDAO;
import com.mycompany.mapa_prog2.controle.ProjetoImpl;
import com.mycompany.mapa_prog2.exceptions.DadoDuplicadoException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) throws DadoDuplicadoException {
        ProjetoDAO dao = new ProjetoImpl();
        int opcao;
        do{
            System.out.println("Registro de projetos");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Alterar");
            System.out.println("3 - Listar");
            System.out.println("4 - Localizar por nome");
            System.out.println("5 - Excluir");
            System.out.println("\n0 - Sair");
            Scanner scannerMenu = new Scanner(System.in);
            opcao = scannerMenu.nextInt();
            try {
                switch (opcao){
                    case 1:
                        Projeto projeto =new Projeto();
                        projeto.setNome(coletarTexto("Digite o nome do projeto"));
                        projeto.setObjetivo(coletarTexto("Digite os objetivos do projeto"));
                        projeto.setNecessidades(coletarTexto("Digite as necessidades do projeto"));
                        projeto.setDataInicio(coletarTexto("Digite a data de início do projeto"));
                        projeto.setDataFinal(coletarTexto("Digite a data final do projeto"));
                        projeto.setStatus(coletarTexto("Digite o status do projeto"));
                        dao.adicionar(projeto);
                        System.out.println("Projeto adicionado com sucesso!");
                        break;
                    case 2:
                        String nomeConsultaAlterar = coletarTexto("Digite o nome do projeto que está buscando para alterar");
                        Projeto projetoNovo = new Projeto();
                        projetoNovo.setNome(coletarTexto("Digite o nome do projeto"));
                        projetoNovo.setObjetivo(coletarTexto("Digite os objetivos do projeto"));
                        projetoNovo.setNecessidades(coletarTexto("Digite as necessidades do projeto"));
                        projetoNovo.setDataInicio(coletarTexto("Digite a data de início do projeto"));
                        projetoNovo.setDataFinal(coletarTexto("Digite a data final do projeto"));
                        projetoNovo.setStatus(coletarTexto("Digite o status do projeto"));
                        dao.alterar(nomeConsultaAlterar, projetoNovo);
                        System.out.println("Projeto alterado com sucesso!");
                        break;
                    case 3:
                        if(dao.listar().size() == 0){
                            System.out.println("Lista vazia!");
                        }else{
                            System.out.println("Lista de projetos:");
                            System.out.println(dao.listar());
                        }
                        break;
                    case 4:
                        String nomeConsultado = coletarTexto("Digite o nome do projeto que está buscando");
                        Projeto projetoEncontrado = dao.consultarPorNome(nomeConsultado);
                        System.out.println(projetoEncontrado);
                        break;
                    case 5:
                        String nomeConsultaExcluir = coletarTexto("Digite o nome do projeto que está buscando para excluir");
                        dao.excluir(nomeConsultaExcluir);
                        System.out.println("Projeto excluído com sucesso!");
                        break;
                    case 0:
                        break;
                }
 
            }catch (InputMismatchException e){
                System.out.println("Não foi possível registrar corretamente a sua opção.");
            } catch (ParseException | DateTimeParseException e) {
                System.out.println("Não foi possível converter a data, tente informar dd/mm/aaaa");
            } catch (DadoConsultadoException | DadoDuplicadoException e) {
                System.out.println(e.getMessage());
            }
            coletarTexto("----");
        }while (opcao!=0);
    }
 
    private static String coletarTexto(String descricao){
        Scanner scanner = new Scanner(System.in);
        System.out.println(descricao);
        return scanner.nextLine();
    }
}