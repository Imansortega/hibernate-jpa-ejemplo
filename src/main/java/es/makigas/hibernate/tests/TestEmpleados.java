package es.makigas.hibernate.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import es.makigas.hibernate.modelo.Empleado;
import javax.persistence.EntityManagerFactory;
import java.util.GregorianCalendar;

public class TestEmpleados {

	private static EntityManager manager;
	private static EntityManagerFactory emf;

	public static void main(String[] args) {
		/* Crear el gestor de persistencia (EM) */
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();

		Empleado e = new Empleado(10L, "perez", "Pepito", new GregorianCalendar(1979, 6, 6).getTime());
		manager.getTransaction().begin();
		manager.persist(e);
		manager.getTransaction().commit();

		imprimirTodo();

		@SuppressWarnings("unchecked")
		List<Empleado> empleados = (List<Empleado>) manager.createQuery("FROM Empleado");
		System.out.println("En esta base de datos hay: " + empleados.size() + " empleados");

	}

	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		List<Empleado> emps = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		System.out.println("Hay: " + emps.size() + " empleados en el sistema");
		for (Empleado emp : emps) {
			System.out.println(emp.toString());
		}

	}
}
