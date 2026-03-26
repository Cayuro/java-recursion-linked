def estadisticaMediana(lista):
    lista.sort()
    n = len(lista)
    if n % 2 == 0:
        mediana = (lista[n//2 - 1] + lista[n//2]) / 2
    else:
        mediana = lista[n//2]
    return mediana

def estadisticaMedia(lista):
    media = sum(lista) / len(lista)
    return media

def estadisticaModa(lista):
    from collections import Counter
    contador = Counter(lista)
    moda = contador.most_common(1)[0][0]
    return moda

def estadisticaDesviacionEstandar(lista):
    media = estadisticaMedia(lista)
    varianza = sum((x - media) ** 2 for x in lista) / len(lista)
    desviacion_estandar = varianza ** 0.5
    return desviacion_estandar

def estadisticaVarianza(lista):
    media = estadisticaMedia(lista)
    varianza = sum((x - media) ** 2 for x in lista) / len(lista)
    return varianza

def estadisticaRango(lista):
    rango = max(lista) - min(lista)
    return rango

def estadisticaPercentil(lista, percentil):
    lista.sort()
    n = len(lista)
    k = (n - 1) * (percentil / 100)
    f = int(k)
    c = k - f
    if f + 1 < n:
        return lista[f] + c * (lista[f + 1] - lista[f])
    else:
        return lista[f]
    