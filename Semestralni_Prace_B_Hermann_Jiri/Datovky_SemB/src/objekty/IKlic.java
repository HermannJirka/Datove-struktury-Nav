/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekty;

/**
 *
 * @author George
 * @param <T>

 */
public interface IKlic<E>{
    public int CompareString(IKlic klic1);
    public int CompareXY(IKlic klic1);
    public E getKey();
    
}
