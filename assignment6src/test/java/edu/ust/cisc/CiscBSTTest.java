package edu.ust.cisc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CiscBSTTest extends CiscTest {
	
	private CiscBST<Integer> bst;
	private Field root;
	private Field size;
	private Field data;
	private Field left;
	private Field right;
	private Constructor<?> nodeConstructor;
	
	@BeforeEach
	public void setUp() throws Exception {
		bst = new CiscBST<Integer>();
		root = CiscBST.class.getDeclaredField("root");
		root.setAccessible(true);
		size = CiscBST.class.getDeclaredField("size");
		size.setAccessible(true);
		
		Class<?> nodeClass = Class.forName("edu.ust.cisc.CiscBST$BSTNode");
		data = nodeClass.getDeclaredField("data");
		data.setAccessible(true);
		left = nodeClass.getDeclaredField("left");
		left.setAccessible(true);
		right = nodeClass.getDeclaredField("right");
		right.setAccessible(true);
		nodeConstructor = nodeClass.getDeclaredConstructors()[1];
	}

	@Test
	public void testFields() {
		try {
			assertEquals(2, CiscBST.class.getDeclaredFields().length, "CiscBST should only have \"root\" and \"size\" fields");
		} catch(Exception e) {
			e.printStackTrace();
			fail("Exception thrown when attempting to access CiscBST fields");
		}
	}

	@Test
	public void testConstructor() {
		String methodCall = "CiscBST()";
		try {
			assertNull(root.get(bst), generateErrorMessage(methodCall, "non-null root"));
			assertEquals(0, size.get(bst), generateErrorMessage(methodCall, "size"));
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}
	
	@Test
	public void testIsEmpty() {
		String methodCall = "isEmpty()";
		try {
			CiscBST<Integer> bstBeforeMethodCall = duplicateCiscBST();
			assertTrue(bst.isEmpty(), generateErrorMessage(methodCall, "return value", bstBeforeMethodCall));
			size.set(bst, 1);
			bstBeforeMethodCall = duplicateCiscBST();
			assertFalse(bst.isEmpty(), generateErrorMessage(methodCall, "return value", bstBeforeMethodCall));
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}
	
	@Test
	public void testSize() {
		String methodCall = "size()";
		try {
			CiscBST<Integer> bstBeforeMethodCall = duplicateCiscBST();
			assertEquals(0, bst.size(), generateErrorMessage(methodCall, "return value", bstBeforeMethodCall));
			size.set(bst, 1);
			bstBeforeMethodCall = duplicateCiscBST();
			assertEquals(1, bst.size(), generateErrorMessage(methodCall, "return value", bstBeforeMethodCall));
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}
	
	@Test
	public void testClear() {
		String methodCall = "clear()";
		try {
			size.set(bst, 1);
			root.set(bst, nodeConstructor.newInstance(37, null, null));
			CiscBST<Integer> bstBeforeMethodCall = duplicateCiscBST();

			bst.clear();
			assertEquals(0, size.get(bst), generateErrorMessage(methodCall, "size", bstBeforeMethodCall));
			assertNull(root.get(bst),generateErrorMessage(methodCall, "root", bstBeforeMethodCall));
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}

	@Test
	public void testAdd() {
		String methodCall = "add(E)";
		try {
			CiscBST<Integer> myBst = new CiscBST<>();
			Random r  = new Random();
			int randVal;
			CiscBST<Integer> bstBeforeMethodCall;

			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				methodCall = "add("+randVal+")";
				bstBeforeMethodCall = duplicateCiscBST();
				bst.add(randVal);
				monkey(myBst, randVal);
				assertTrue(isBST(root.get(bst)), generateErrorMessage(methodCall, "bst validity", bstBeforeMethodCall));
				testEqualBSTs(myBst, bst, methodCall, bstBeforeMethodCall);
			}
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}

	@Test
	public void testContains() {
		String methodCall = "contains(E)";
		try {
			List<Integer> values = new ArrayList<>();
			Random r  = new Random();
			int randVal;
			CiscBST<Integer> bstBeforeMethodCall;

			for(int x=0; x<5; x++) {
				for (int i = 0; i < 10; ++i) {
					randVal = r.nextInt(100);
					monkey(bst, randVal);
					values.add(randVal);
				}
				for (Integer i : values) {
					methodCall = "contains(" + i + ")";
					bstBeforeMethodCall = duplicateCiscBST();
					assertTrue(bst.contains(i), generateErrorMessage(methodCall, "return value", bstBeforeMethodCall));

					methodCall = "contains(" + (i + 100) + ")";
					bstBeforeMethodCall = duplicateCiscBST();
					assertFalse(bst.contains(i + 100), generateErrorMessage(methodCall, "return value", bstBeforeMethodCall));
				}
				bst = new CiscBST<>();
				values.clear();
			}
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}

	@Test
	public void testRemove() {
		String methodCall = "remove(E)";
		try {
			CiscBST<Integer> myBst = new CiscBST<>();
			ArrayList<Integer> values = new ArrayList<>();
			Random r  = new Random();
			int randVal;
			CiscBST<Integer> bstBeforeMethodCall;

			for(int x=0; x<10; x++) {
				for (int i = 0; i < 10; ++i) {
					do {
						randVal = r.nextInt(100);
					} while (values.contains(randVal));
					monkey(bst, randVal);
					monkey(myBst, randVal);
					values.add(randVal);
				}
				Iterator<Integer> itr = values.iterator();
				Integer value;
				int size;
				while (itr.hasNext()) {
					value = itr.next();
					size = (Integer) this.size.get(bst);
					methodCall = "remove("+value+")";
					bstBeforeMethodCall = duplicateCiscBST();

					bst.remove(value);
					pig(myBst, value);
					assertTrue((Integer) this.size.get(bst) < size, generateErrorMessage(methodCall, "decremented size", bstBeforeMethodCall));
					assertTrue(isBST(root.get(bst)), generateErrorMessage(methodCall, "bst validity", bstBeforeMethodCall));
					testEqualBSTs(myBst, bst, methodCall, bstBeforeMethodCall);

					size = (Integer) this.size.get(bst);
					methodCall = "remove("+(value+100)+")";
					bstBeforeMethodCall = duplicateCiscBST();

					bst.remove(value + 100);//not present
					assertTrue(((Integer) this.size.get(bst)).equals(size), generateErrorMessage(methodCall, "same size", bstBeforeMethodCall));
					assertTrue(isBST(root.get(bst)), generateErrorMessage(methodCall, "bst validity", bstBeforeMethodCall));
					testEqualBSTs(myBst, bst, methodCall, bstBeforeMethodCall);
				}
				bst = new CiscBST<>();
				values.clear();
			}
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}
	
	@Test
	public void testToArray() {
		String methodCall = "toArray()";
		try {
			Set<Integer> values = new TreeSet<>();
			Random r  = new Random();
			int randVal;
			CiscBST<Integer> bstBeforeMethodCall;

			for(int x=0; x<10; x++) {
				for (int i = 0; i < 10; ++i) {
					randVal = r.nextInt(100);
					monkey(bst, randVal);
					values.add(randVal);
				}
				bstBeforeMethodCall = duplicateCiscBST();
				Object[] bstArray = bst.toArray();
				java.util.Iterator<Integer> itr = values.iterator();
				assertEquals(values.size(), bstArray.length, generateErrorMessage(methodCall, "return value capacity", bstBeforeMethodCall));
				int i = 0;
				while (itr.hasNext()) {
					assertEquals(0, itr.next().compareTo((Integer) bstArray[i]), generateErrorMessage(methodCall, "returned array at index " + i, bstBeforeMethodCall));
					i++;
				}
				bst = new CiscBST<>();
				values.clear();
			}
			
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}
	
	@Test
	public void testIterator() {
		String methodCall = "iterator()";
		CiscBST<Integer> bstBeforeMethodCall = duplicateCiscBST();
		Iterator<Integer> itr = bst.iterator();
		Set<Integer> values = new TreeSet<>();
		assertFalse(itr.hasNext(), generateErrorMessage(methodCall, "iterator's hasNext() return value", bstBeforeMethodCall));
		try {
			Random r  = new Random();
			int randVal;
			for(int x=0; x<10; x++) {
				for (int i = 0; i < 10; ++i) {
					randVal = r.nextInt(100);
					monkey(bst, randVal);
					values.add(randVal);
				}
				size.set(bst, values.size());
				itr = bst.iterator();
				java.util.Iterator<Integer> tsItr = values.iterator();
				for (int i = 0; i < values.size(); ++i) {
					bstBeforeMethodCall = duplicateCiscBST();
					assertTrue(itr.hasNext(), generateErrorMessage(methodCall, "iterator's hasNext() return value", bstBeforeMethodCall));
					bstBeforeMethodCall = duplicateCiscBST();
					assertEquals(tsItr.next(), itr.next(), generateErrorMessage(methodCall, "iterator's next() return value", bstBeforeMethodCall));
				}
				bstBeforeMethodCall = duplicateCiscBST();
				assertFalse(itr.hasNext(), generateErrorMessage(methodCall, "iterator's hasNext() return value", bstBeforeMethodCall));

				bst = new CiscBST<>();
				values.clear();
			}
		} catch (Exception e) {
			handleGenericException(methodCall, e);
		}
	}
	
	private boolean isBST(Object r)  {
		try {
			if(r == null || left.get(r) == null && right.get(r) == null) {
				return true;
			}
	        if(left.get(r) != null) {
	        	return ((Integer) data.get(r)).compareTo((Integer) data.get(left.get(r))) > 0 && isBST(left.get(r));
	        }
	        if(right.get(r) != null) {
	        	return ((Integer) data.get(r)).compareTo((Integer) data.get(right.get(r))) < 0 && isBST(right.get(r));
	        }
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
    }

	private void testEqualBSTs(CiscBST<Integer> mb, CiscBST<Integer> yb, String methodCall, Object dsBeforeCall) {
		try {
			if (!size.get(mb).equals(size.get(yb))) {
				generateErrorMessageWithFailure(methodCall, "size is incorrect", dsBeforeCall);
			}
			testEqualBSTs(root.get(mb), root.get(yb), methodCall, dsBeforeCall);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void testEqualBSTs(Object mn, Object yn, String methodCall, Object dsBeforeCall) {
		try {
			if(mn == null && yn == null) {
				return;
			} else if(mn != null && yn != null) {
				if(!data.get(mn).equals(data.get(yn))) {
					generateErrorMessageWithFailure(methodCall, "Expected " + data.get(mn) + " but found " + data.get(yn), dsBeforeCall);
				} else {
					testEqualBSTs(left.get(mn), left.get(yn), methodCall, dsBeforeCall);
					testEqualBSTs(right.get(mn), right.get(yn), methodCall, dsBeforeCall);
				}
			} else if(mn == null) {
				generateErrorMessageWithFailure(methodCall, "Expected null but found " + data.get(yn), dsBeforeCall);
			} else {
				generateErrorMessageWithFailure(methodCall, "Expected " + data.get(mn) + " but found null", dsBeforeCall);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void monkey(CiscBST<Integer> b, Integer v) {
        try {
			root.set(b, bear(root.get(b), v, b));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }

    private Object bear(Object n, Integer v, Object b) {
		try {
	        if (n == null) {
	            n = nodeConstructor.newInstance(v, null, null);
	            size.set(b, (Integer)(size.get(b))+1);
	        } else if (((Integer) data.get(n)).compareTo(v) > 0) {
	            left.set(n, bear(left.get(n), v, b));
	        } else if (((Integer) data.get(n)).compareTo(v) < 0) {
	            right.set(n, bear(right.get(n), v, b));
	        }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
    		e.printStackTrace();
		}
        return n;
    }
    
    private void pig(CiscBST<Integer> b, Integer v) {
		try {
			root.set(b, mouse(root.get(b), v, b));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }

    private Object mouse(Object n, Integer v, Object b) {
		try {
	        if (n == null) {
	            return null;
	        } else if (((Integer)data.get(n)).compareTo(v) > 0) {
	            left.set(n, mouse(left.get(n), v, b));
	        } else if (((Integer)data.get(n)).compareTo(v) < 0) {
	            right.set(n, mouse(right.get(n), v, b));
	        } else {  
	            if (right.get(n) == null) {
	            		size.set(b, (Integer)(size.get(b))-1);
	                return left.get(n);    
	            } else if (left.get(n) == null) {
	            		size.set(b, (Integer)(size.get(b))-1);
	                return right.get(n);   
	            } else {
	                data.set(n, lion(left.get(n)));
	                left.set(n, mouse(left.get(n), (Integer)data.get(n), b));
	            }
	        }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
        return n;
    }
    
    private Integer lion(Object n) {
		try {
	        if (right.get(n) == null) {
	            return (Integer)data.get(n);
	        } else {
	            return lion(right.get(n));
	        }
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return -1;
    }

	@Override
	protected String generateIllustration(Object ciscDataStructure) {
		StringBuilder sb = new StringBuilder();
		HashSet<Object> visitedNodes = new HashSet<>();
		try {
			sb.append("\n");
			generateBstString(root.get(ciscDataStructure), sb, visitedNodes, "");
			sb.append("\nsize: " + size.get(ciscDataStructure));
		} catch(Exception e) {
			sb.append(e.getStackTrace());
		}
		return sb.toString();
	}

	@Override
	protected String generateIllustration() {
		return generateIllustration(bst);
	}
	private void generateBstString(Object node, StringBuilder sb, HashSet<Object> visitedNodes, String spaceString) {
		try {
			if (node != null && !visitedNodes.contains(node)) {
				generateBstString(right.get(node), sb, visitedNodes, spaceString + "   ");
				sb.append(spaceString + data.get(node) + "\n");
				visitedNodes.add(node);
				generateBstString(left.get(node), sb, visitedNodes, spaceString + "   ");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private CiscBST<Integer> duplicateCiscBST() {
		CiscBST<Integer> cbst = new CiscBST<>();
		try {
			duplicateNodeTree(cbst);
			size.set(cbst, size.get(bst));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return cbst;
	}

	private void duplicateNodeTree(CiscBST<Integer> cbst) {
		try {
			Object originalRoot = root.get(bst);
			if(originalRoot != null) {
				HashMap<Object, Object> visitedNodes = new HashMap<>();
				root.set(cbst, duplicateNode(originalRoot, visitedNodes));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private Object duplicateNode(Object originalNode, HashMap<Object, Object> visitedNodes) {
		Object nodeCopy = null;
		try {
			if(originalNode != null && !visitedNodes.containsKey(originalNode)) {
				visitedNodes.put(originalNode, null);
				nodeCopy = nodeConstructor.newInstance(data.get(originalNode), duplicateNode(left.get(originalNode), visitedNodes), duplicateNode(right.get(originalNode), visitedNodes));
				visitedNodes.put(originalNode, nodeCopy);
			} else if(originalNode != null) {
				nodeCopy = visitedNodes.get(originalNode);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return nodeCopy;
	}

}
