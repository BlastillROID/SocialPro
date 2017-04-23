<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * MailList
 *
 * @ORM\Table(name="mail_list", indexes={@ORM\Index(name="to_id", columns={"to_id"}), @ORM\Index(name="from_id", columns={"from_id"}), @ORM\Index(name="IDX_E9B36B71C8776F01", columns={"mail_id"})})
 * @ORM\Entity
 */
class MailList
{
    /**
     * @var \Employee
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Employee")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="to_id", referencedColumnName="id")
     * })
     */
    private $to;

    /**
     * @var \Employee
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Employee")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="from_id", referencedColumnName="id")
     * })
     */
    private $from;

    /**
     * @var \Mails
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Mails")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="mail_id", referencedColumnName="mail_id")
     * })
     */
    private $mail;


}

