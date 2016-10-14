/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekty;

/**
 *
 * @author George
 */
public interface IGridBlock<E> {

    int getIndexBlock();

    boolean isJeUzavren();

    E odeberZBloku(E data);

    void setIndexBlock(int indexBlock);

    boolean vlozDataDoBlocku(E data);
    
}
