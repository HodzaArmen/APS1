# Programabilni kalkulator

V programskem jeziku java napišite programabilni kalkulator, ki omogoča napredno računanje (programiranje) s celimi (int) števili in nizi. Kalkulator omogoča:
<ul>
  <li>računanje aritmetičnih izrazov v obratnem poljskem zapisu (angl. reverse polish notation)
</li>
  <li>preprosto konkatenacijsko programiranje (s pomočjo sklada)
</li>
  <li>izvedbo preprostih programskih konstruktov (pogojev, zank, funkcij).
</li>
</ul>

Kalkulator naj izraze bere s standardnega vhoda. Vsaka vrstica predstavlja en izraz, sestavljen iz več delov (nizov) - vsak predstavlja  bodisi veljaven ukaz, celo število ali niz znakov. Med posameznimi dela izraza je vedno (vsaj) en presledek. Predpostavite lahko, da so pri testiranju vsi vhodi sintaktično in semantično pravilni.  

Rešitev implementirajte s pomočjo 42 skladov: glavnega (indeks 0) in pomožnih (z indeksi od 1 do 41). Velika večina ukazov deluje nad glavnim skladom (z indeksom 0). Pri ukazih, ki se lahko izvajajo nad poljubnim skladom, je indeks sklada posebej določen s številom na vrhu glavnega sklada. 

Kalkulator naj podpira naslednje ukaze oz. operacije (nad glavnim skladom):
<ul>
  <li>echo - v vrstici izpiše vrh sklada 0 (sklad pusti nespremenjen); če je sklad prazen, izpiše prazno vrstico
</li>
  <li>pop - odstrani vrh sklada 0
</li>
  <li>dup - podvoji vrh sklada 0 (x -> x x)
</li>
  <li>dup2 - podvoji par na vrhu sklada 0 (x y -> x y x y)
</li>
  <li>swap - zamenja vrhnja dva elementa sklada 0 (x y -> y x)
</li>
</ul>

Naslednje operacije zamenjajo vrh glavnega  sklada z ustreznim rezultatom (x -> y):
<ul>
  <li>char - vrh sklada 0 zamenja z znakom, ki ima ASCII/Unicode kodo vrha sklada
</li>
  <li>even - vrh sklada 0 zamenja z 1, če je vrh sod, sicer z 0
</li>
  <li>odd - vrh sklada 0 zamenja z 1, če je vrh lih, sicer z 0
</li>
  <li>! - vrh sklada 0 zamenja s faktorielo vrha
</li>
  <li>len - vrh sklada 0 zamenja z dolžino elementa na vrhu
</li>
</ul>

Naslednje operacije zamenjajo vrhnja dva elementa glavnega sklada z ustreznim rezultatom (x y -> r):
<ul>
  <li><> - primerja zgornja dva elementa (x y) sklada 0 in na sklad porine 1 (če x <> y) ali 0 (če x == y)
</li>
  <li>< - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x < y) ali 0 (sicer)
</li>
  <li><= - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x <= y) ali 0 (sicer)
</li>
  <li>== - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x == y) ali 0 (sicer)
</li>
  <li>> - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x > y) ali 0 (sicer)
</li>
  <li>>= - primerja zgornja dva elementa sklada 0 in na sklad porine 1 (če x >= y) ali 0 (sicer)
</li>
  <li>+ - na sklad 0 porine vsoto vrhnjih dveh elementov sklada
</li>
  <li>- - na sklad 0 porine razliko vrhnjih dveh elementov sklada
</li>
  <li>* - na sklad 0 porine zmnožek vrhnjih dveh elementov sklada
</li>
  <li>/ - na sklad 0 porine kvocient (celoštevilsko deljenje) vrhnjih dveh elementov sklada
</li>
  <li>% - na sklad 0 porine ostanek po deljenju elementa pod vrhom z elementom na vrh
</li>
  <li>. - stakne (združi, zlepi) vrhnja dva elementa sklada 0 v en element (x y -> xy)
</li>
  <li>rnd - na sklad 0 porine naključno število, ki ima vrednost >= x in <= y 
</li>
</ul>

Naslednje operacije omogočajo izvedbo pogojnega stavka (izpolnjenost pogoja hranimo v interni spremeljivki):
<ul>
  <li>then  – z glavnega sklada 0 vzame vrhnje število; če je to različno od 0, nastavi izpolnjenost pogoja na true, sicer pa na false
</li>
  <li>else – zanika izpolnjenost pogoja
</li>
  <li>?... – vsak ukaz, ki se začne z ?, se izpolni (ali pa ne) glede na prednastavljeno izpolnjenost pogoja
</li>
</ul>

Za delo s poljubnim skladom (glavnim ali pomožnimi) imamo na voljo spodnje ukaze. Pri tem velja, da število na vrhu glavnega sklada 0 določa indeks sklada, nad katerim se izvaja ukaz:
<ul>
  <li>print - v vrstici izpiše vsebino sklada (z indeksom, ki je podan na vrhu glavnega sklada 0) od dna do vrha (sklad ostane nespremenjen)
</li>
  <li>clear – izprazne sklad (z indeksom, ki je podan na vrhu glavnega sklada 0)
</li>
  <li>run – izvede vse ukaze na (pomožnem) skladu (z indeksom, ki je podan na vrhu glavnega sklada 0) od dna do vrha (sklad ostane nespremenjen)
</li>
  <li>loop - izvede vse ukaze na (pomožnem) skladu (z indeksom, ki je podan na vrhu glavnega sklada 0) od dna do vrha (sklad ostane nespremenjen), pri čemer to ponovi tolikokrat, kot je podano s  številom pod vrhom sklada 0
</li>
  <li>fun – na pomožni sklad  (z indeksom, ki je podan na vrhu glavnega sklada 0) zapiše toliko naslednjih ukazov, kolikor določa število pod vrhom glavnega sklada 0 
</li>
  <li>move – z glavnega sklada prenese na pomožni sklad  (z indeksom, ki je podan na vrhu glavnega sklada 0) toliko elementov, kolikor določa število pod vrhom glavnega sklada 0 (elementi se prenesejo eden za drugim)
</li>
  <li>reverse - obrne vrstni red vseh elementov na skladu  (z indeksom, ki je podan na vrhu glavnega sklada 0) - u v x y z -> z y x v u
</li>
</ul>

Če ukaz ni na seznamu zgoraj naštetih, potem gre za element (število ali niz), ki se porine na vrh glavnega sklada 0 (push).

## Primeri izvajanja
V vseh primerih so vrstice, ki se začnejo z >, vnešene v program, ostale pa so izpis programa. Program zaženemo z

java Naloga1

Nekaj primerov izvajanja operacij:

```
> 0 -1 3 5 -7 11 -13 17 dup2 echo pop echo swap 0 print
17
-13

0 -1 3 5 -7 11 -13 -13 17 
```
```
> 151 131 + echo -100 140 - echo + echo
282
-240
42
```
```
> 3 5 11 17 0 print + + 10 * 0 print * 11 / echo

3 5 11 17

3 330
90
```
```
> 6 ! echo 42 == echo even 0 print
720
0
1
```
```
> 65 90 rnd echo char echo
66
B
```
```
> 0 1 2 3 4 3 4 4 1 fun dup 0 reverse swap 2 2 move 0 print 1 print 2 print
0 1 2 3 4
dup 0 reverse swap
4 3
```
```
> 0 1 2 3 3 1 fun 0 reverse dup 0 print 1 run 0 print 2 1 loop 0 print
0 1 2 3 
3 2 1 0 0  
33 2 1 0 0 0
```
```
> 7 3 1 2 5 1 fun == then ?dup2 else ?+ 1 run 0 print
10
```
```
> 9 1 fun dup 0 reverse swap % dup then ?1 ?run 24 10 0 print 1 run pop echo
24 10
2
```
```
> 3 1 fun 0 100 rnd 3 2 fun 5 1 loop 7 3 fun dup2 <= then ?pop else ?swap ?pop 3 4 fun 4 3 loop 1 print 2 print 3 print 4 print 2 run 0 print 4 run 0 print
0 100 rnd
1 5 loop
dup2 <= then ?pop else ?swap ?pop
4 3 loop 
34 96 12 48 24
12
```

## Drugi napotki in namigi
Pri izvedbi lahko uporabite le javansko knjižnico java.util.Scanner. Vse ostale knjižnice niso dovoljene oz. potrebne - izrecno niso dovoljene knjižnice, ki vsebujejo javanske zbirke (npr. ArrayList) iz Collection Framework-a

Za predstavitev skladov najprej zapišite vmesnik (abstraktni podatkovni tip) Stack z ustreznimi operacijami, nato pa ga implementirajte s pomočjo javanskega polja. Velikost sklada lahko omejite na 64. Za izvedbo več skladov napišite še vnesnik Sequence.

Upoštevajte, da je celoten izraz (program) napisan v eni vrstici, torej so pred obdelavo vsake vrstice vhoda vsi skladi prazni ter izpolnjenost pogoja nastavljena na **FALSE**. 

Kot aritmetične operacije lahko uporabljate operacije nad javanskimi primitivnimi tipi (int), za določanje naključnega števila pa metodo Math.random().
